package pizza.order;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    //
    // fields
    //

    @Value("${app.order.delivery-time-in-minutes}")
    private Integer deliveryTimeInMinutes = 30;

    @Value("${app.order.discount-days}")
    private List<String> discountDays = new ArrayList<>();

    @Value("${app.order.discount-rate}")
    private double discountRate = 0.0d;

    //
    // injected beans
    //

    private final CustomerService customerService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    //
    // constructors and setup
    //

    public OrderService(CustomerService customerService, ProductService productService, OrderRepository orderRepository) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void dumpConfiguration() {
        System.out.println(discountRate);
        System.out.printf("""
                Using configuration:
                - deliveryTimeInMinutes=%d
                - discountDays=%s
                - discountRate=%2.2f
                %n""", deliveryTimeInMinutes, discountDays, discountRate);
    }

    //
    // business logic
    //

    /**
     * Places an order for a Customer identified by his/her phone number.
     * <p>
     * The kind and number of products ordered are given in the <code>productQuantities</code> map.
     *
     * @param phoneNumber       required argument, the phone number to identify the Customer with
     * @param productQuantities required argument, a mapping of product-ids to their desired
     *                          quantities
     * @return the {@link Order} entity which contains the total price and estimated time of
     * delivery
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // make sure customer exists -- throws exception if it doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

        // increase
        this.customerService.increaseOrderCount(customer.getId());

        // calculate total price
        Double totalPrice = this.productService.getTotalPrice(productQuantities);

        // discounts
        double todaysDiscountRate = getTodaysDiscountRate();
        double discountedTotalPrice = totalPrice * (1.0 - todaysDiscountRate / 100.0);
        System.out.println("Reducing price of order from " + totalPrice + " to " + discountedTotalPrice
                + " due to today's discount of " + todaysDiscountRate + "%");

        // create order
        Order order = new Order(
                customer,
                discountedTotalPrice,
                LocalDateTime.now().plusMinutes(this.deliveryTimeInMinutes));

        // persist and return it
        return orderRepository.save(order);
    }

    private double getTodaysDiscountRate() {
        String nameOfDayOfWeek = LocalDate.now().getDayOfWeek().name();
        if (this.discountDays.contains(nameOfDayOfWeek)) {
            return this.discountRate;
        } else {
            return 0.0d;
        }
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }
}

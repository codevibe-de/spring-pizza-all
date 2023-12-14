package pizza.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pizza.aop.LogExecutionTime;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Profile("default | order")
public class OrderService {

    //
    // fields
    //

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Value("${app.order.delivery-time-in-minutes:30}")
    private Integer deliveryTimeInMinutes;

    @Value("${app.order.discount-days:''}")
    private List<String> discountDays;

    @Value("${app.order.discount-rate:0.0}")
    private double discountRate;

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
        LOG.debug("""
                Using configuration:
                - deliveryTimeInMinutes={}
                - discountDays={}
                - discountRate={}
                """, deliveryTimeInMinutes, discountDays, discountRate);
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
    @LogExecutionTime
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
        LOG.debug(
                "Reducing price of order from {} to {} due to today's discount of {}%",
                totalPrice, discountedTotalPrice, discountRate
        );

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

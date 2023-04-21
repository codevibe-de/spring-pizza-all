package pizza.order;

import org.springframework.stereotype.Service;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    //
    // fields
    //

    private final Integer deliveryTimeInMinutes = 30;

    private final Map<String, Double> dailyDiscounts = new HashMap<>();

    private final ArrayList<Order> orders;

    //
    // injected beans
    //

    private final CustomerService customerService;

    private final ProductService productService;

    //
    // constructors and setup
    //

    public OrderService(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
        this.orders = new ArrayList<>();
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
     * @param productQuantities required argument, a mapping of product-ids to their desired quantities
     * @return the {@link Order} entity which contains the total price and estimated time of delivery
     */
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // make sure customer exists -- throws exception if it doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

        // calculate total price
        Double totalPrice = this.productService.getTotalPrice(productQuantities);

        // discounts
        String nameOfDayOfWeek = LocalDate.now().getDayOfWeek().name();
        Double discountRate = this.dailyDiscounts.getOrDefault(nameOfDayOfWeek, 0.0);
        Double discountedTotalPrice = totalPrice * (1.0 - discountRate / 100.0);
        System.out.println("Reducing price of order from " + totalPrice + " to " + discountedTotalPrice
                + " due to today's discount of " + discountRate + "%");

        // create order
        Order order = new Order(
                customer,
                discountedTotalPrice,
                LocalDateTime.now().plusMinutes(this.deliveryTimeInMinutes));

        // persist and return it
        order.setId(orders.size() + 1);
        this.orders.add(order);
        return order;
    }

    public Iterable<Order> getOrders() {
        return Collections.unmodifiableList(this.orders);
    }
}

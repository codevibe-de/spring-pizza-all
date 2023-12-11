package pizza.order;

import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderService {

    //
    // fields
    //

    private Integer deliveryTimeInMinutes = 30;

    private List<String> discountDays = new ArrayList<>();

    private double discountRate = 0.0d;

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
     * @param productQuantities required argument, a mapping of product-ids to their desired
     *                          quantities
     * @return the {@link Order} entity which contains the total price and estimated time of
     * delivery
     */
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // make sure customer exists -- throws exception if it doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

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
        order.setId(orders.size() + 1);
        this.orders.add(order);
        return order;
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
        return Collections.unmodifiableList(this.orders);
    }
}

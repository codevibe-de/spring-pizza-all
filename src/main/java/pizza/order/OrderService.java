package pizza.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    //
    // fields
    //

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Value("${app.order.delivery-time-in-minutes}")
    private Integer deliveryTimeInMinutes = 30;

    @Value("#{${app.order.daily-discounts}}")
    private Map<String, Double> dailyDiscounts = new HashMap<>();

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
        LOG.debug(
                "Using configuration:\n  deliveryTimeInMinutes={}\n  dailyDiscounts={}",
                deliveryTimeInMinutes, dailyDiscounts
        );
    }

    //
    // business logic
    //

    @Transactional(propagation = Propagation.REQUIRED)
    public Order placeOrder(String phoneNumber, Map<String, Integer> productQuantities) {
        // make sure customer exists -- throws exception if it doesn't
        Customer customer = this.customerService.getCustomerByPhoneNumber(phoneNumber);

        // increase
        this.customerService.increaseOrderCount(customer.getId());

        // calculate total price
        Double totalPrice = this.productService.getTotalPrice(productQuantities);

        // discounts
        String nameOfDayOfWeek = LocalDate.now().getDayOfWeek().name();
        Double discountRate = this.dailyDiscounts.getOrDefault(nameOfDayOfWeek, 0.0);
        Double discountedTotalPrice = totalPrice * (1.0 - discountRate / 100.0);
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

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }
}

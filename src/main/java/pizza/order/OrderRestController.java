package pizza.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    //
    // --- constants ---
    //

    static final String ROOT = "/orders";
    public static final String GREETING_ENDPOINT = ROOT + "/greeting";
    public static final String PLACE_ORDER_ENDPOINT = ROOT;
    public static final String GET_MANY_ENDPOINT = ROOT;

    //
    // --- injected beans ---
    //

    private final OrderService orderService;
    private final String greeting;

    //
    // --- constructors and setup ---
    //

    public OrderRestController(OrderService orderService, @Value("${app.order.greeting:Hello!}") String greeting) {
        this.orderService = orderService;
        this.greeting = greeting;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GREETING_ENDPOINT)
    public String sayHello() {
        return this.greeting;
    }

    @PostMapping(PLACE_ORDER_ENDPOINT)
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        return this.orderService.placeOrder(
                orderRequest.phoneNumber,
                orderRequest.itemQuantities);
    }

    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Order> getOrders() {
        return this.orderService.getOrders();
    }
}

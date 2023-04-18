package pizza.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("default | order")
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


    @Operation(summary = "Place an order", description = "Places an order by providing your phone number and a set of item quantities. " +
            "This is a map of product-ids to the number of products you want.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "The order has been placed successfully"),
            @ApiResponse(responseCode = "404", description = "Either a required Customer or Product entity could not be found - check details")
    })
    @PostMapping(PLACE_ORDER_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(
            @RequestBody OrderRequest orderRequest,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        return this.orderService.placeOrder(
                currentUser.getUsername(),
                orderRequest.itemQuantities);
    }


    @GetMapping(GET_MANY_ENDPOINT)
    public Iterable<Order> getOrders() {
        return this.orderService.getOrders();
    }
}

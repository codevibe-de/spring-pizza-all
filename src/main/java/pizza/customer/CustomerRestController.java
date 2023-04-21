package pizza.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pizza.order.OrderService;

@RestController
public class CustomerRestController {

    //
    // --- constants ---
    //

    private static final String ROOT = "/customers";
    public static final String GET_ONE_ENDPOINT = ROOT + "/{id}";
    public static final String GET_ALL_ENDPOINT = ROOT;
    public static final String CREATE_ENDPOINT = ROOT;
    public static final String GET_ORDERS_FOR_CUSTOMER = ROOT + "/{customerId}/orders";

    //
    // --- injected beans ---
    //

    private final CustomerService customerService;
    private final OrderService orderService;

    //
    // --- constructors and setup ---
    //

    public CustomerRestController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    //
    // --- REST endpoints ---
    //

    @GetMapping(GET_ONE_ENDPOINT)
    public Customer getCustomer(@PathVariable long id) throws CustomerNotFoundException {
        return this.customerService.getCustomer(id);
    }


    @GetMapping(GET_ALL_ENDPOINT)
    public Iterable<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @PostMapping(CREATE_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return this.customerService.createCustomer(
                new Customer(
                        createCustomerRequest.fullName(),
                        createCustomerRequest.address(),
                        createCustomerRequest.phoneNumber()
                )
        );
    }

    @GetMapping(GET_ORDERS_FOR_CUSTOMER)
    public Iterable<CustomerOrderResponse> getOrdersForCustomer(@PathVariable long customerId) {
        // load customer to check it really exists (throws exception if not)
        Customer customer = this.customerService.getCustomerById(customerId);
        // get orders -- but remove the
        return orderService.getOrdersForCustomer(customer).stream()
                .map(CustomerOrderResponse::of)
                .toList();
    }
}

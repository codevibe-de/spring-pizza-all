package pizza.order;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import pizza.customer.Address;
import pizza.customer.Customer;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * GraphQL controller for Order queries.
 * <p>
 * This controller provides GraphQL API endpoints to retrieve orders
 * with optional customer and address details.
 */
@Controller
public class OrderGraphQLController {

    private final OrderService orderService;

    public OrderGraphQLController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Query to retrieve all orders.
     *
     * @return List of all orders
     */
    @QueryMapping(name = "orders")
    public List<Order> getAllOrders() {
        Iterable<Order> ordersIterable = orderService.getOrders();
        return StreamSupport.stream(ordersIterable.spliterator(), false)
                .toList();
    }

    /**
     * Query to retrieve a specific order by ID.
     *
     * @param id The order ID
     * @return The order if found, null otherwise
     */
    @QueryMapping
    public Order order(@Argument Long id) {
        return orderService.getOrder(id);
    }

    /**
     * Field resolver for Order.customer field.
     */
    @SchemaMapping(typeName = "Order", field = "customer")
    public Customer customer(Order order) {
        return order.getCustomer();
    }

    /**
     * Field resolver for Customer.address field.
     */
    @SchemaMapping(typeName = "Customer", field = "address")
    public Address address(Customer customer) {
        return customer.getAddress();
    }
}

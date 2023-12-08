package pizza;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.ProductService;

import java.util.Map;

@Component
@Order(50)
public class LogicRunner implements ApplicationRunner {

    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public LogicRunner(ProductService productService, CustomerService customerService, OrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) {
        var product = productService.getProduct("P-10");
        System.out.println(product);

        // get a customer
        var customer = customerService.getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // place an order
        var order = orderService.placeOrder(
                "+49 123 456789",
                Map.ofEntries(
                        Map.entry("P-10", 3),
                        Map.entry("S-03", 1)
                )
        );
        System.out.println(order);
    }
}


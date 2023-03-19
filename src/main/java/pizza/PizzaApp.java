package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.ProductService;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // run boot app
        var context = SpringApplication.run(App.class, args);

        // get a product
        var product = context.getBean(ProductService.class).getProduct("P-10");
        System.out.println(product);

        // get a customer
        var customer = context.getBean(CustomerService.class).getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // place an order
        var order = context.getBean(OrderService.class).placeOrder(
                "+49 123 456789",
                ofEntries(
                        entry("P-10", 3),
                        entry("S-03", 1)
                )
        );
        System.out.println(order);
    }
}

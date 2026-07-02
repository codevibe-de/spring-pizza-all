package pizza;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.Product;
import pizza.product.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Order(2)
@ConditionalOnProperty(name = "logic-runner.enabled", matchIfMissing = true)
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
        var products = toList(productService.getAllProducts());
        System.out.println("\nProducts:");
        System.out.println(AsciiTable.getTable(products, Arrays.asList(
                new Column().header("ID").headerAlign(HorizontalAlign.RIGHT).dataAlign(HorizontalAlign.RIGHT).with(Product::getProductId),
                new Column().header("Name").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(Product::getName),
                new Column().header("Price").headerAlign(HorizontalAlign.RIGHT).dataAlign(HorizontalAlign.RIGHT).with(p -> String.format("%.2f EUR", p.getPrice()))
        )));

        var customers = toList(customerService.getAllCustomers());
        System.out.println("\nCustomers:");
        System.out.println(AsciiTable.getTable(customers, Arrays.asList(
                new Column().header("ID").headerAlign(HorizontalAlign.RIGHT).dataAlign(HorizontalAlign.RIGHT).with(c -> String.valueOf(c.getId())),
                new Column().header("Name").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(Customer::getFullName),
                new Column().header("Phone").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(Customer::getPhoneNumber),
                new Column().header("Address").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(c ->
                        c.getAddress().getStreet() + ", " + c.getAddress().getPostalCode() + " " + c.getAddress().getCity())
        )));

        if (!products.isEmpty() && !customers.isEmpty()) {
            var order = orderService.placeOrder(
                    customers.get(0).getPhoneNumber(),
                    Map.of(products.get(0).getProductId(), 2,
                            products.get(products.size() > 1 ? 1 : 0).getProductId(), 1)
            );
            System.out.println("\nOrder placed:");
            System.out.println(AsciiTable.getTable(List.of(order), Arrays.asList(
                    new Column().header("ID").headerAlign(HorizontalAlign.RIGHT).dataAlign(HorizontalAlign.RIGHT).with(o -> String.valueOf(o.getId())),
                    new Column().header("Customer").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(o -> o.getCustomer().getFullName()),
                    new Column().header("Total").headerAlign(HorizontalAlign.RIGHT).dataAlign(HorizontalAlign.RIGHT).with(o -> String.format("%.2f EUR", o.getTotalPrice())),
                    new Column().header("Est. Delivery").headerAlign(HorizontalAlign.LEFT).dataAlign(HorizontalAlign.LEFT).with(o -> o.getEstimatedTimeOfDelivery().toLocalTime().toString())
            )));
        }
    }

    static <T> List<T> toList(Iterable<T> iterable) {
        var list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list;
    }
}
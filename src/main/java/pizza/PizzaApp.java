package pizza;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import org.h2.jdbcx.JdbcDataSource;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.Product;
import pizza.product.ProductService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PizzaApp {

    public static void main(String[] args) {
        // Instantiate beans ---
        // hint: you only need the DataSource if you want to use the JdbcProductRepository
        // (instead of HashMapProductRepository)
        H2TcpServer h2TcpServer = startDatabase();
        DataSource dataSource = createDataSource();
        // TODO create services and required helper instances here
        ProductService productService = null;
        CustomerService customerService = null;
        OrderService orderService = null;
        new DataLoader.Sample(productService, customerService).run();

        // Work with the data:
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

        // Stop database to make app terminate ---
        stopDatabase(h2TcpServer);
    }

    static H2TcpServer startDatabase() {
        H2TcpServer h2TcpServer = new H2TcpServer();
        h2TcpServer.start();
        return h2TcpServer;
    }

    static void stopDatabase(H2TcpServer h2TcpServer) {
        if (h2TcpServer != null) {
            h2TcpServer.stop();
        }
    }

    static DataSource createDataSource() {
        // use a H2 DataSource implementation
        var dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/training.spring.pizza");

        // run a script to set up the database schema (=tables)
        new SchemaScriptRunner(dataSource).run();

        // return the data source for others to work with
        return dataSource;
    }

    static <T> List<T> toList(Iterable<T> iterable) {
        var list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list;
    }
}

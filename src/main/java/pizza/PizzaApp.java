package pizza;

import org.h2.jdbcx.JdbcDataSource;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.ProductService;

import javax.sql.DataSource;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

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

        // Get a product ---
        var product = productService.getProduct("P-10");
        System.out.println(product);

        // Get a customer ---
        var customer = customerService.getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // Place an order ---
        var order = orderService.placeOrder(
                "+49 123 456789",
                ofEntries(
                        entry("P-10", 3),
                        entry("S-03", 1)
                )
        );
        System.out.println(order);

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
}

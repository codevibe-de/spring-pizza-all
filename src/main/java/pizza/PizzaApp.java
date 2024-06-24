package pizza;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.JdbcProductRepository;
import pizza.product.ProductService;

import javax.sql.DataSource;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@SpringBootApplication
public class PizzaApp {

    public static void main(String[] args) {
        // start Spring context
        SpringApplication.run(PizzaApp.class, args);

        // Instantiate beans ---
        H2TcpServer h2TcpServer = startDatabase();
        DataSource dataSource = createDataSource();
        ProductService productService = new ProductService(new JdbcProductRepository(dataSource));
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService(customerService, productService);
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

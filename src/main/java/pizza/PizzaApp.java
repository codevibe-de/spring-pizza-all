package pizza;

import org.h2.jdbcx.JdbcDataSource;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.InMemoryProductRepository;
import pizza.product.ProductService;

import javax.sql.DataSource;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class PizzaApp {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    private H2TcpServer h2TcpServer;

    public PizzaApp() {
        // hint: you only need the DataSource if you want to use the JdbcProductRepository
        // (instead of InMemoryProductRepository)
        DataSource dataSource = createDataSource();
        this.customerService = new CustomerService();
        this.productService = new ProductService(new InMemoryProductRepository());
        this.orderService = new OrderService(this.customerService, this.productService);
        new DataLoader.Sample(this.productService, this.customerService).run();
    }

    DataSource createDataSource() {
        // start a H2 database instance
        this.h2TcpServer = new H2TcpServer();
        this.h2TcpServer.start();

        // use a H2 DataSource implementation
        var dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/training.spring.pizza");

        // run a script to set up the database schema (=tables)
        new SchemaScriptRunner(dataSource).run();

        // return the data source for others to work with
        return dataSource;
    }

    // --- bean getters for business logic below (not really required but helpful later) ---

    ProductService getProductService() {
        return this.productService;
    }

    CustomerService getCustomerService() {
        return this.customerService;
    }

    OrderService getOrderService() {
        return this.orderService;
    }

    // --- business logic ---

    public static void main(String[] args) {
        // launch pizzeria
        var app = new PizzaApp();

        // get a product
        var product = app.getProductService().getProduct("P-10");
        System.out.println(product);

        // get a customer
        var customer = app.getCustomerService().getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // place an order
        var order = app.getOrderService().placeOrder(
                "+49 123 456789",
                ofEntries(
                        entry("P-10", 3),
                        entry("S-03", 1)
                )
        );
        System.out.println(order);

        // we need to stop the server so that VM terminates properly
        if (app.h2TcpServer != null) {
            app.h2TcpServer.stop();
        }
    }
}

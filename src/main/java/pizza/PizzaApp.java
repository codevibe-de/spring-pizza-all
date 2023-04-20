package pizza;

import org.h2.jdbcx.JdbcDataSource;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.ProductService;

import javax.sql.DataSource;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class PizzaApp {

    public PizzaApp() {
    }

    ProductService getProductService() {
        // todo
        return null;
    }

    CustomerService getCustomerService() {
        // todo
        return null;
    }

    OrderService getOrderService() {
        // todo
        return null;
    }

    DataSource getDataSource() {
        // start a H2 database instance
        new H2Launcher().run();

        // use a H2 DataSource implementation
        var dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9092/~/training.spring-boot.pizza");

        // run a script to set up the database schema (=tables)
        new H2ScriptRunner(dataSource).run();

        // return the data source for others to work with
        return dataSource;
    }

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
    }
}

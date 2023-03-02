package pizza;

import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.order.Order;
import pizza.order.OrderService;
import pizza.product.Product;
import pizza.product.ProductService;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class App {

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

    public static void main(String[] args) {
        // start the p
        var app = new App();

        // get a product
        var product = app.getProductService().getProduct("P-001");
        System.out.println(product);

        // get a customer
        var customer = app.getCustomerService().getCustomerByPhoneNumber("+49 123 456789");
        System.out.println(customer);

        // place an order
        var order = app.getOrderService().placeOrder(
                "+49 123 456789",
                ofEntries(
                        entry("P-001", 3),
                        entry("S-003", 1)
                )
        );
        System.out.println(order);
    }
}

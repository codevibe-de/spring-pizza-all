package pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pizza.customer.Address;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.Product;
import pizza.product.ProductService;

/**
 * The <code>DataLoader</code> is an abstract class implementing the {@link Runnable}
 * interface, which adds basic helper methods for adding products and customers.
 * <p>
 * Some concrete extensions of this abstract class are implemented as static inner classes.
 */
public abstract class DataLoader implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final ProductService productService;
    private final CustomerService customerService;

    public DataLoader(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    protected void createProduct(String productId, String name, double price) {
        Product p = this.productService.createProduct(new Product(productId, name, price));
        LOG.debug("Created: {}", p);
    }

    protected Address createAddress(String street, String postalCode, String city) {
        return new Address(street, postalCode, city);
    }

    protected void createCustomer(String fullName, String phoneNumber, Address address) {
        Customer c = this.customerService.createCustomer(new Customer(
                fullName,
                address,
                phoneNumber
        ));
        LOG.debug("Created: {}", c);
    }

    //
    // --- concrete implementations ---
    //

    @Component("none")
    public static class None extends DataLoader {
        public None() {
            super(null, null);
        }

        @Override
        public void run() {
        }
    }


    @Component("sample")
    public static class Sample extends DataLoader {
        public Sample(ProductService productService, CustomerService customerService) {
            super(productService, customerService);
        }

        @Override
        public void run() {
            createProduct("S-01", "Thunfisch Salat", 6.90);
            createProduct("S-02", "Salat Italiano", 7.90);
            createProduct("S-03", "Romana Salat", 8.90);
            createProduct("P-10", "Pizza Margarita", 5.50);
            createProduct("P-11", "Pizza Capricciosa", 7.50);
            createProduct("P-12", "Pizza Spinat und Feta", 7.00);

            var address1 = createAddress("Wasserstr. 123", "40302", "Atlantis");
            var address2 = createAddress("Schlossallee 1", "88776", "Monopolhausen");

            createCustomer("Enrico Pallazzo", "+49 123 456789", address1);
            createCustomer("Elizabeth Magie", "+1 77 551237", address2);
        }
    }

}

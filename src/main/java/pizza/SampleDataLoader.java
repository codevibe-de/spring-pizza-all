package pizza;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import pizza.customer.Address;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.Product;
import pizza.product.ProductService;

import javax.annotation.PostConstruct;

@Component
public interface SampleDataLoader extends Runnable {

    @Component("noop")
    class NoOpDataLoader implements SampleDataLoader {
        @Override
        public void run() {
        }
    }

    @Component("small")
    class SmallDataLoader implements SampleDataLoader {

        private final ProductService productService;
        private final CustomerService customerService;

        public SmallDataLoader(ProductService productService, CustomerService customerService) {
            this.productService = productService;
            this.customerService = customerService;
        }

        @Override
        public void run() {
            System.out.println("Loading sample data...");

            createProduct("S-01", "Thunfisch Salat", 6.90);
            createProduct("S-02", "Salat Italiano", 7.90);
            createProduct("S-03", "Romana Salat", 8.90);
            createProduct("P-10", "Pizza Margarita", 5.50);
            createProduct("P-11", "Pizza Capricciosa", 7.50);
            createProduct("P-12", "Pizza Spinat und Feta", 7.00);

            var address1 = createAddress("Wasserstr. 123", "40302", "Atlantis");

            createCustomer("Enrico Pallazzo", "+49 123 456789", address1);
        }

        protected void createProduct(String productId, String name, double price) {
            this.productService.createProduct(new Product(productId, name, price));
        }

        protected Address createAddress(String street, String postalCode, String city) {
            return new Address(street, postalCode, city);
        }

        protected void createCustomer(String fullName, String phoneNumber, Address address) {
            this.customerService.createCustomer(new Customer(
                    fullName,
                    address,
                    phoneNumber
            ));
        }
    }

}

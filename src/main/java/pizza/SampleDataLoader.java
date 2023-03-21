package pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pizza.customer.Address;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.Product;
import pizza.product.ProductService;

import java.util.Optional;

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

        private static final Logger LOG = LoggerFactory.getLogger(SmallDataLoader.class);

        private final Optional<ProductService> optionalProductService;
        private final Optional<CustomerService> optionalCustomerService;

        public SmallDataLoader(
                Optional<ProductService> optionalProductService,
                Optional<CustomerService> optionalCustomerService) {
            this.optionalProductService = optionalProductService;
            this.optionalCustomerService = optionalCustomerService;
        }

        @Override
        public void run() {
            LOG.info("Loading sample data...");

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
            this.optionalProductService.ifPresent(
                    ps -> ps.createProduct(new Product(productId, name, price))
            );
        }

        protected Address createAddress(String street, String postalCode, String city) {
            return new Address(street, postalCode, city);
        }

        protected void createCustomer(String fullName, String phoneNumber, Address address) {
            this.optionalCustomerService.ifPresent(
                    cs -> cs.createCustomer(new Customer(fullName, address, phoneNumber))
            );
        }
    }

}

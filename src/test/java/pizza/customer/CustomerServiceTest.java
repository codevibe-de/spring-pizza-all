package pizza.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pizza.DataLoadRunner;
import pizza.DataLoader;
import pizza.product.ProductService;

import static org.assertj.core.api.Assertions.assertThat;

// Note: Context is configured in inner @TestConfiguration class below
@DataJpaTest
public class CustomerServiceTest {

    @MockitoBean
    ProductService productService;

    @Autowired
    CustomerService customerService;

    /**
     * Tests that we can retrieve a Customer by his/her phone-number using the CustomerService
     */
    @Test
    void getCustomerByPhoneNumber() {
        // given
        String phoneNumber = "+49 123 456789";

        // when
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @TestConfiguration
    @ComponentScan("pizza.customer") // loads EVERY bean from package including the CustomerService
    @Import({DataLoadRunner.class, DataLoader.Sample.class})
    static class TestConfig {
    }
}

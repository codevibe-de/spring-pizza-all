package pizza.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pizza.SampleDataLoader;
import pizza.SampleDataLoaderRunner;
import pizza.product.ProductService;

import static org.assertj.core.api.Assertions.assertThat;

// Note: Context is configured in inner @TestConfiguration class below
@ExtendWith({SpringExtension.class})
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    SampleDataLoaderRunner sampleDataLoaderRunner;

    /**
     * Tests that we can retrieve a Customer by his/her phone-number using the CustomerService
     */
    @Test
    void getCustomerByPhoneNumber() {
        // given
        sampleDataLoaderRunner.run(null);
        String phoneNumber = "+49 123 456789";

        // when
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @TestConfiguration
    @ComponentScan("pizza.customer") // loads EVERY bean from package including the CustomerService
    @Import({SampleDataLoaderRunner.class, SampleDataLoader.SmallDataLoader.class})
    static class TestConfig {
        @MockBean // we don't care what the ProductService does, we just need that bean in the context for data loading
        ProductService productService;
    }
}

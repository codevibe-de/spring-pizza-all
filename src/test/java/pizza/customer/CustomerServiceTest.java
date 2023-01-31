package pizza.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class requires only a single bean -- that's why we don't annotate this class with @SpringBootTest but just
 * add Junit support for Spring for injecting beans.
 * The bean itself is defined in the inner class Beans below.
 */
@ExtendWith({SpringExtension.class})
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getCustomerByPhoneNumber() {
        // given
        var phoneNumber = "+1 111-222-333";
        var fullName = "Toni Test";
        customerService.createCustomer(
                new Customer(fullName, null, phoneNumber)
        );

        // when
        var customer = customerService.getCustomerByPhoneNumber(phoneNumber);

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getId()).isNotEqualTo(0);
        assertThat(customer.getFullName()).isEqualTo(fullName);
    }

    // populates the application-context with just this bean
    @TestConfiguration
    static class Beans {
        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
    }
}
package pizza.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// This test actually doesn't really require spring to start up a context.
// We could just instantiate the CustomerService ourselves since it doesn't integrate
// with any other bean.
@SpringBootTest
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

}
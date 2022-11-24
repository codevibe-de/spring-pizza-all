package pizza.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/context.xml")
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
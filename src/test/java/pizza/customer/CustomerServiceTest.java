package pizza.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CustomerTestContextConfiguration.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomer() {
        // given

        // when
        var customer = customerService.getCustomerByPhoneNumber("+49 123 456789");

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getFullName()).isEqualTo("Enrico Pallazzo");
    }
}

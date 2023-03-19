package pizza.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pizza.SampleDataLoaderRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
@Import(CustomerTestContextConfiguration.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SampleDataLoaderRunner sampleDataLoaderRunner;

    @BeforeEach
    public void init() {
        sampleDataLoaderRunner.run(null);
    }

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

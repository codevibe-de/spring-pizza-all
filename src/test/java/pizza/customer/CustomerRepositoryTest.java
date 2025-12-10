package pizza.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAllCustomerPhoneNumberAndZip() {
        // when
        List<CustomerPhoneNumberAndPostalCode> projections = customerRepository.findAllProjectedBy();

        // then
        Assertions.assertThat(projections).anySatisfy(c -> {
            Assertions.assertThat(c.getPhoneNumber()).isEqualTo("+49 123 456789");
            Assertions.assertThat(c.getAddress().getPostalCode()).isEqualTo("40302");
        });
    }
}
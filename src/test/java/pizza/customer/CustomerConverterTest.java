package pizza.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerConverterTest {

    @Autowired
    private ConversionService conversionService;

    @Test
    void testCreateCustomerRequestDataConverter() {
        // given
        var address = new Address("street", "postalCode", "city");
        var input = new CreateCustomerRequestData("fullName", address, "phoneNumber");

        // when
        var customer = conversionService.convert(
                input,
                Customer.class
        );

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getFullName()).isEqualTo(input.getFullName());
        assertThat(customer.getPhoneNumber()).isEqualTo(input.getPhoneNumber());
    }
}

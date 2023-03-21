package pizza.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pizza.customer.Customer;
import pizza.customer.CustomerRepository;
import pizza.product.ProductNotFoundException;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void placeOrder() {
        // when
        this.orderService.placeOrder(
                "+49 123 456789",
                Map.of("S-01", 1)
        );
    }

    /**
     * Tests that the order-count is increased although the order process exists with an exception.
     */
    @Test
    void placeOrder_customerOrderCountIncreasesDespiteTransactionFail() {
        // given -- create new customer and check order-count is really 0
        var customerPhoneNumber = UUID.randomUUID().toString();
        Customer customer = customerRepository.save(
                new Customer("Trans Action", null, customerPhoneNumber)
        );
        assertThat(customerRepository.findById(customer.getId()))
                .hasValueSatisfying(c -> assertThat(c.getOrderCount()).isEqualTo(0));

        // when -- place order for missing product
        Map<String, Integer> itemQuantities = Collections.singletonMap("i-dont-exist", 1);
        assertThatThrownBy(
                () -> orderService.placeOrder(customerPhoneNumber, itemQuantities)
        ).isInstanceOf(ProductNotFoundException.class);

        // then -- check count after test
        assertThat(customerRepository.findById(customer.getId()))
                .hasValueSatisfying(c -> assertThat(c.getOrderCount()).isEqualTo(1));
    }
}

package pizza.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pizza.customer.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void findOrderSummariesByCustomerId() {
        // given
        var customer1 = customerRepository.save(
                new pizza.customer.Customer("John Doe", null, null)
        );
        var customer2 = customerRepository.save(
                new pizza.customer.Customer("Jane Smith", null, null)
        );

        var order1 = orderRepository.save(
                new Order(customer1, 15.50, java.time.LocalDateTime.now().plusHours(1))
        );
        var order2 = orderRepository.save(
                new Order(customer1, 22.00, java.time.LocalDateTime.now().plusHours(2))
        );
        orderRepository.save(
                new Order(customer2, 18.75, java.time.LocalDateTime.now().plusHours(1))
        );

        // when
        var summaries = orderRepository.findOrderSummariesByCustomerId(customer1.getId());

        // then
        assertThat(summaries).hasSize(2);
        assertThat(summaries)
                .extracting(OrderRepository.OrderSummary::orderId)
                .containsExactlyInAnyOrder(order1.getId(), order2.getId());
        assertThat(summaries)
                .extracting(OrderRepository.OrderSummary::customerFullName)
                .containsOnly("John Doe");
    }
}

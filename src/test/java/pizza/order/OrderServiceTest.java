package pizza.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void placeOrder() {
        // when
        this.orderService.placeOrder(
                "0170-222-333-444",
                Map.of("S-01", 1)
        );
    }
}

package pizza.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "app.order.delivery-time-in-minutes=33",
        "app.order.discount-days=TUESDAY,WEDNESDAY, THURSDAY",
        "app.order.discount-rate=1.23"
})
public class OrderPropertiesTest {

    @Autowired
    OrderProperties orderProperties;

    @Test
    void properties() {
        assertThat(this.orderProperties).isNotNull();
        assertThat(this.orderProperties.deliveryTimeInMinutes()).isEqualTo(33);
        assertThat(this.orderProperties.discountDays()).containsOnly("TUESDAY", "WEDNESDAY", "THURSDAY");
        assertThat(this.orderProperties.discountRate()).isEqualTo(1.23);
    }
}

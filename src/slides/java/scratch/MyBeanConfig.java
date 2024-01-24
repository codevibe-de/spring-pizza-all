package scratch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MyBeanConfig {
    @Bean
    @Order(1)
    MyBean myBean1() { return new MyBean(); }
    @Bean
    @Order(22)
    MyBean myBean2() { return new MyBean(); }
}

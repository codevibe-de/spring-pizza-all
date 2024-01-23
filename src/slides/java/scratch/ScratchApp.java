package scratch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class ScratchApp {
    public static void main(String[] args) {
        SpringApplication.run(ScratchApp.class);
    }

    @Bean
    @Primary
    @Order(10)
    StringBuilder stringBuilder() {
        return new StringBuilder("1");
    }

    @Bean
    @Order(3)
    StringBuilder stringBuilder3() {
        return new StringBuilder("3");
    }

    @Bean
    @Order(2)
    StringBuilder stringBuilder2() {
        return new StringBuilder("2");
    }
}

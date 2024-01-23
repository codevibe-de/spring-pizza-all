package scratch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScratchApp {
    public static void main(String[] args) {
        SpringApplication.run(ScratchApp.class);
    }

    @Bean
    StringBuilder stringBuilder() {
        return new StringBuilder();
    }
}

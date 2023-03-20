package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PizzaApp {

    public static void main(String[] args) {
        // run boot app
        SpringApplication.run(PizzaApp.class, args);
    }

}

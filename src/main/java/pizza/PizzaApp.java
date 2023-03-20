package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaApp {

    public static void main(String[] args) {
        // run boot app
        SpringApplication.run(PizzaApp.class, args);
    }

    @Bean(initMethod = "run", destroyMethod = "stop")
    H2Launcher h2Launcher() {
        return new H2Launcher();
    }
}

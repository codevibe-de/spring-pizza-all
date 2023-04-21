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

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2TcpServer h2Launcher() {
        return new H2TcpServer();
    }
}

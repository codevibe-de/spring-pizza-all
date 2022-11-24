package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import pizza.order.OrderProperties;

@SpringBootApplication()
@ServletComponentScan
@ImportResource("classpath:/context.xml")
@EnableConfigurationProperties(OrderProperties.class)
public class PizzaApiApplication {

    public static void main(String[] args) {
        System.out.println("Los geht's!");
        SpringApplication.run(PizzaApiApplication.class, args);
    }

}

package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PizzaApiApplication {

    public static void main(String[] args) {
        System.out.println("Los geht's!");
        SpringApplication.run(PizzaApiApplication.class, args);
    }

}

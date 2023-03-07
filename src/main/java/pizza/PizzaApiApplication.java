package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaApiApplication {

    public static void main(String[] args) {
        System.out.println("Los geht's!");
        SpringApplication.run(PizzaApiApplication.class, args);
    }

}

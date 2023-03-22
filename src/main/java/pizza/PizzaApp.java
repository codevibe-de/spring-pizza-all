package pizza;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@EnableConfigurationProperties
public class PizzaApp {

    public static void main(String[] args) {
        // run boot app
        SpringApplication.run(PizzaApp.class, args);
    }

    // this fixes the problem that the application won't start anymore after adding spring-integration libraries since
    // a second ConversionService instance is added by those. the solution is to make the MVC one a 'primary' bean, which
    // is used in case of ambiguity
    @Bean
    @Primary
    public ConversionService primaryConversionService(@Qualifier("mvcConversionService") ConversionService conversionService) {
        return conversionService;
    }
}

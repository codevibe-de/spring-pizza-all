package pizza;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties
public class PizzaApp {

    public static void main(String[] args) {
        // run boot app
        SpringApplication.run(PizzaApp.class, args);
    }

    // This fixes the problem that the application won't start anymore after adding spring-integration libraries since
    // a second ConversionService instance is added by those. The solution is to make the MVC one a 'primary' bean, which
    // is used in case of ambiguity.
    // However, since some test cases don't start the full context we might encounter that bean to be missing
    // hence the use of Optional.
    @Bean
    @Primary
    public ConversionService primaryConversionService(@Qualifier("mvcConversionService") Optional<ConversionService> optionalConversionService) {
        return optionalConversionService.orElse(null);
    }
}

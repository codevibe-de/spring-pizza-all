package pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PizzaApp {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApp.class, args);
    }

    // required for making Thymeleaf print correct currency symbol
    @Bean
    public LocaleResolver localeResolver() {
        var sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.GERMANY);
        return sessionLocaleResolver;
    }
}

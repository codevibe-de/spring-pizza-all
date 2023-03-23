package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import pizza.auth.WhoAmIController;
import pizza.customer.CustomerRestController;
import pizza.order.OrderRestController;
import pizza.product.ProductRestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class ResourceServerSecurityConfig {

    // generated via https://dinochiesa.github.io/jwt/
    private final String symmetricKey = "Undoubtedly-Daring-Orbit-6888-40";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(GET, WhoAmIController.ME_ENDPOINT).permitAll()
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).permitAll()
                .antMatchers(CustomerRestController.ROOT).hasRole("STAFF")
                .antMatchers(ProductRestController.ROOT).hasRole("STAFF")
                .antMatchers(GET, OrderRestController.GET_MANY_ENDPOINT).hasRole("MANAGER")
                .antMatchers("/**").denyAll()
            .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .csrf().disable(); // only for testing
        // @formatter:on
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var symmetricKeyBytes = symmetricKey.getBytes();
        SecretKey key = new SecretKeySpec(symmetricKeyBytes, 0, symmetricKeyBytes.length, "AES");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}

package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pizza.auth.MyUserDetailsService;
import pizza.auth.WhoAmIController;
import pizza.customer.CustomerService;
import pizza.order.OrderRestController;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(GET, WhoAmIController.ME_ENDPOINT).permitAll()
                        .requestMatchers(GET, OrderRestController.GREETING_ENDPOINT).permitAll()
                        .requestMatchers(POST, OrderRestController.PLACE_ORDER_ENDPOINT).hasRole("CUSTOMER")
                        .anyRequest().hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable); // DEV ONLY
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(CustomerService customerService, PasswordEncoder passwordEncoder) {
        return new MyUserDetailsService(customerService, passwordEncoder);
    }
}

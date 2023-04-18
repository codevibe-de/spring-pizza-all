package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(GET, WhoAmIController.ME_ENDPOINT).permitAll()
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).permitAll()
                .antMatchers(POST, OrderRestController.PLACE_ORDER_ENDPOINT).hasRole("CUSTOMER")
                .antMatchers("/**").hasRole("ADMIN")
            .and()
                .httpBasic()
            .and()
                .csrf().disable(); // only for testing
        // @formatter:on
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(CustomerService customerService, PasswordEncoder passwordEncoder) {
        return new MyUserDetailsService(customerService, passwordEncoder);
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

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
import pizza.customer.CustomerRestController;
import pizza.order.OrderRestController;
import pizza.product.ProductRestController;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class WebSecurityConfig {

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
                .httpBasic()
            .and()
                .formLogin()
            .and()
                .csrf().disable(); // only for testing
        // @formatter:on
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

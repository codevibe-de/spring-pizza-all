package pizza.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pizza.customer.CustomerNotFoundException;
import pizza.customer.CustomerService;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    // generated with https://bcrypt-generator.com/ (12 rounds) from password "pwd"
    public static final String DEFAULT_PASSWORD_HASH = "$2a$12$TNAt7offtGR2STrVOhXngOwo4xv/1TiYbQ2CPCeDWL4B8.qGOZ0py";

    private static final String ADMIN_USERNAME = "admin";

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public MyUserDetailsService(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(ADMIN_USERNAME)) {
            return new User(
                    username,
                    DEFAULT_PASSWORD_HASH,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        } else {
            try {
                var customer = customerService.getCustomerByPhoneNumber(username);
                return new User(
                        customer.getPhoneNumber(),
                        passwordEncoder.encode(customer.getAddress().getPostalCode()),
                        List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
                );
            } catch (CustomerNotFoundException e) {
                throw new UsernameNotFoundException("not found", e);
            }
        }
    }
}

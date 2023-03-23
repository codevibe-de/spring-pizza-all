package pizza.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pizza.customer.CustomerNotFoundException;
import pizza.customer.CustomerService;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    // generated with https://bcrypt-generator.com/ (12 rounds) from password "pwd"
    public static final String DEFAULT_PASSWORD_HASH = "$2a$12$TNAt7offtGR2STrVOhXngOwo4xv/1TiYbQ2CPCeDWL4B8.qGOZ0py";

    private final CustomerService customerService;

    public MyUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // check if username describes a Customer's phone-number -- then load Customer as user
        if (username.startsWith("+")) {
            try {
                var customer = customerService.getCustomerByPhoneNumber(username);
                return new User(
                        customer.getFullName(),
                        DEFAULT_PASSWORD_HASH,
                        List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
                );
            } catch (CustomerNotFoundException e) {
                throw new UsernameNotFoundException("not found", e);
            }
        } else {
            // otherwise we just generate a default user based on the given username.
            return new User(
                    username,
                    DEFAULT_PASSWORD_HASH,
                    createAuthorities(username)
            );
        }
    }

    private static List<SimpleGrantedAuthority> createAuthorities(String username) {
        return switch (username) {
            case "manager" -> List.of(
                    new SimpleGrantedAuthority("ROLE_STAFF"),
                    new SimpleGrantedAuthority("ROLE_MANAGER")
            );
            case "no-access" -> List.of();
            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_STAFF")
            );
        };
    }

}

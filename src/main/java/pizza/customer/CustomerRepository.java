package pizza.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("default | customer | order")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(String pn);

}

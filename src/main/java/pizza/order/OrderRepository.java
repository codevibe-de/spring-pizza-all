package pizza.order;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import pizza.customer.Customer;

@Profile("default | order")
public interface OrderRepository extends JpaRepository<Order, Long> {

    Iterable<Order> findAllByCustomer(Customer customer);

}

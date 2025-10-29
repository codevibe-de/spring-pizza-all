package pizza.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.customer.Customer;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Iterable<Order> findAllByCustomer(Customer customer);

}

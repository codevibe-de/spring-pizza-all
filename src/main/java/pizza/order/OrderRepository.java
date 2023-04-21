package pizza.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.customer.Customer;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

}

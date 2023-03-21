package pizza.order;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("default | order")
public interface OrderRepository extends JpaRepository<Order, Long> {
}

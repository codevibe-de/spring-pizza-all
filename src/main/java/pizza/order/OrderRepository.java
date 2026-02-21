package pizza.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pizza.customer.Customer;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Iterable<Order> findAllByCustomer(Customer customer);

    @Query("""
              SELECT new pizza.order.OrderRepository$OrderSummary(o.id, c.fullName)
              FROM Order o
              JOIN o.customer c
              WHERE c.id = :customerId
            """)
    List<OrderSummary> findOrderSummariesByCustomerId(Long customerId);


    // --- data classes ---

    record OrderSummary(Long orderId, String customerFullName) {
    }


}

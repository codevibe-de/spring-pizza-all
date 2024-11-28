package scratch.datajpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;
import pizza.order.Order;

import java.util.List;

public class CustomizedOrderRepositoryImpl implements CustomizedOrderRepository {

    private final EntityManager em;

    public CustomizedOrderRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(Order.class);
    }

    @Override
    public List<Order> findOverdueOrders() {
        var builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        // ...
        return this.em.createQuery(query).getResultList();
    }
}

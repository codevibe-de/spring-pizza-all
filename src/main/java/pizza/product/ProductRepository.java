package pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default | product | order")
public interface ProductRepository extends JpaRepository<Product, String> {
}

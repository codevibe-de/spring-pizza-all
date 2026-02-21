package pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("default | product | order")
public interface ProductRepository extends JpaRepository<Product, String> {
}

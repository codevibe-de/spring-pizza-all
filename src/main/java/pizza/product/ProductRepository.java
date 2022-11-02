package pizza.product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    boolean existsById(String productId);

    Collection<Product> findAll();

    Optional<Product> findById(String productId);
}

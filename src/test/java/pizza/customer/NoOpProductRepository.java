package pizza.customer;

import pizza.product.Product;
import pizza.product.ProductRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class NoOpProductRepository implements ProductRepository {

    @Override
    public Product save(Product product) {
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        return false;
    }

    @Override
    public Collection<Product> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.empty();
    }
}

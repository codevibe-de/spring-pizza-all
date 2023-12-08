package pizza.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> productsMap = new HashMap<>();

    @Override
    public Product save(Product product) {
        productsMap.put(product.getProductId(), product);
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        return productsMap.containsKey(productId);
    }

    @Override
    public Collection<Product> findAll() {
        return productsMap.values();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(productsMap.get(productId));
    }
}

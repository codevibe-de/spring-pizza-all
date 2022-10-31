package de.auinger.training.spring_boot.product;

import java.util.Collection;

public interface ProductRepository {

    Product save(Product product);

    boolean existsById(String productId);

    Collection<Product> findAll();

    Product findById(String productId);
}

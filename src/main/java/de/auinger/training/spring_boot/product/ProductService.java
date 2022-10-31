package de.auinger.training.spring_boot.product;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    //
    // constructors and setup
    //

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //
    // business logic
    //

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId);
    }

    public Double getTotalPrice(Map<String, Integer> productQuantities) {
        // TODO
        return null;
    }

    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            throw new IllegalStateException();
        }
        return productRepository.save(product);
    }
}

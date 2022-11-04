package pizza.product;

import java.util.Map;

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
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("For id " + productId));
    }

    public Double getTotalPrice(Map<String, Integer> productQuantities) {
        double result = 0.0;
        // TODO implement calculation
        return result;
    }

    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            throw new IllegalStateException();
        }
        return productRepository.save(product);
    }
}

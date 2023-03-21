package pizza.product;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("default | product | order")
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
        // loop over each map entry (which is productId -> quantity) and map each entry to the product's price
        // multiplied by desired quantity. Then sum all up and that is our total.
        // To avoid annoying floating point arithmetic problems we calculate everything in cent and divide later
        // back to full Euro.
        var totalPriceInCent = productQuantities.entrySet().stream()
                .mapToDouble(entry -> {
                    Product product = getProduct(entry.getKey());
                    return product.getPrice() * entry.getValue() * 100;
                })
                .sum();
        return Math.floor(totalPriceInCent) / 100.0;
    }

    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            throw new IllegalStateException("The product-repository already contains a product with id " + product.getProductId());
        }
        return productRepository.save(product);
    }
}

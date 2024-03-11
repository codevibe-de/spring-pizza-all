package scratch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public ProductRepository productRepository() {
        var r = new HashMapProductRepository();
        r.setEvictionPolicy(STRICT);
        return r;
    }
}

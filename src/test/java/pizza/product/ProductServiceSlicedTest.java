package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ProductServiceSlicedTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    /**
     * Tests that an IllegalStateException is thrown if we create more than one Product with the same id.
     */
    @Test
    void createProduct__failsForDuplicateId() {
        // given
        var product = new Product(
                UUID.randomUUID().toString().substring(0, 10),
                "createProduct__failsForDuplicateId() Test",
                0.01
        );

        // when
        productService.createProduct(product);  // first insert must be ok
        Assertions.assertThatThrownBy(
                () -> productService.createProduct(product)
        ).isInstanceOf(IllegalStateException.class);
    }

}

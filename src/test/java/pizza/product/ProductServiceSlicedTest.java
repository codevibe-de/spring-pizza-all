package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@Import({ProductService.class})
@EnableAutoConfiguration
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

package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    /**
     * Tests that we can get a Product by id and that the result contains all expected data.
     */
    @Test
    void getProduct() {
        // given
        var productId = UUID.randomUUID().toString().substring(0, 10); // DB limits the id to 10 chars
        var productName = "getProduct() Test";
        var productPrice = 1.23;
        productRepository.save(
                new Product(productId, productName, productPrice)
        );

        // when
        var product = productService.getProduct(productId);

        // then
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductId()).isEqualTo(productId);
        Assertions.assertThat(product.getName()).isEqualTo(productName);
        Assertions.assertThat(product.getPrice()).isEqualTo(productPrice);
    }


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

package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class ProductServiceMockedTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    /**
     * Tests that the total price for a range of products and quantities is calculated correctly.
     */
    @Test
    void getTotalPrice() {
        // given
        var someProduct = new Product(
                UUID.randomUUID().toString().substring(0, 10),
                "someProduct",
                2.10
        );
        var anotherProduct = new Product(
                UUID.randomUUID().toString().substring(0, 10),
                "anotherProduct",
                6.99
        );
        Mockito.when(productRepository.findById(someProduct.getProductId())).thenReturn(Optional.of(someProduct));
        Mockito.when(productRepository.findById(anotherProduct.getProductId())).thenReturn(Optional.of(anotherProduct));

        // when
        var totalPrice = productService.getTotalPrice(
                Map.of(
                        someProduct.getProductId(), 3,
                        anotherProduct.getProductId(), 1
                )
        );

        // then -- we expect 3 * 2.10 + 6.99 = 13,29
        Assertions.assertThat(totalPrice).isEqualTo(13.29);
    }
}

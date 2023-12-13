package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findOneByExample() {
        // given
        var product1 = productRepository.save(new Product("001", "First Product", 1.00));
        productRepository.save(new Product("002", "Second Product", 2.00));

        // when
        var optionalProduct = productRepository.findOne(
                Example.of(new Product(null, "First Product", null))
        );

        // then
        Assertions.assertThat(optionalProduct).isNotNull();
        Assertions.assertThat(optionalProduct).containsSame(product1);
    }


    @Test
    void findAllByExample() {
        // given
        productRepository.save(new Product("001", "First Product", 1.00));
        productRepository.save(new Product("002", "Second Product", 2.00));

        // when
        var exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", endsWith());
        // when
        var productList = productRepository.findAll(
                Example.of(new Product(null, "Product", null), exampleMatcher)
        );

        // then
        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList).hasSize(2);
    }
}

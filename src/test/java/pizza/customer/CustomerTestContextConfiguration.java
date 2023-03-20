package pizza.customer;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pizza.SampleDataLoader;
import pizza.SampleDataLoaderRunner;
import pizza.product.ProductRepository;

@TestConfiguration
@ComponentScan({"pizza.customer", "pizza.product"})
@Import({
        SampleDataLoaderRunner.class,
        SampleDataLoader.SmallDataLoader.class,
        DataSourceAutoConfiguration.class
})
public class CustomerTestContextConfiguration {

    // Demonstrates replacing an existing bean with regular bean definition mechanics.
    // This can be, of course, replaced by just a @MockBean declaration in the test class itself.
    @Bean("productJdbcDao")
    ProductRepository mockedProductRepository() {
        return Mockito.mock(ProductRepository.class);
    }
}

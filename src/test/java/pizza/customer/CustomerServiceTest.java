package pizza.customer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pizza.SampleDataLoader;
import pizza.SampleDataLoaderRunner;
import pizza.product.ProductRepository;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void getCustomerByPhoneNumber() {
        // when
        var phoneNumber = "+49 123 456789";
        var customer = customerService.getCustomerByPhoneNumber(phoneNumber);

        // then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getId()).isNotEqualTo(0);
        assertThat(customer.getFullName()).isEqualTo("Enrico Pallazzo");
    }

    @TestConfiguration
    @ComponentScan({"pizza.customer", "pizza.product"})
    @Import({SampleDataLoader.SmallDataLoader.class, SampleDataLoaderRunner.class})
    static class Beans {
        @Bean("productJdbcDao") // overwrite existing bean
        public ProductRepository noOpProductRepo() {
            return new NoOpProductRepository();
        }
        @Bean
        public DataSource dummyDataSource() {
            return Mockito.mock(DataSource.class);
        }
    }
}
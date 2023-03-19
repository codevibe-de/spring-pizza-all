package pizza.customer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pizza.SampleDataLoader;
import pizza.SampleDataLoaderRunner;

@Configuration
@ComponentScan({"pizza.customer", "pizza.product"})
@Import({
        SampleDataLoaderRunner.class,
        SampleDataLoader.SmallDataLoader.class
})
public class CustomerTestContextConfiguration {
}

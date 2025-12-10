package pizza;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Performs loading of sample data after the context has started up.
 * <p>
 * We do not want to use @PostConstruct in any {@code DataLoader} implementation itself since this
 * caused timing issues when using the default Spring Boot H2 data source. The data could be loaded
 * but wasn't available afterward for querying (empty tables).
 */
@Component
@Order(1)
public class DataLoadRunner implements ApplicationRunner {

    private final DataLoader dataLoader;

    public DataLoadRunner(
            Map<String, DataLoader> dataLoaders,
            @Value("${app.data-loader:sample}") String beanName
    ) {
        this.dataLoader = Optional.ofNullable(dataLoaders.get(beanName))
                .orElseThrow(() -> new NoSuchBeanDefinitionException(beanName));
    }

    @Override
    public void run(ApplicationArguments args) {
        this.dataLoader.run();
    }
}

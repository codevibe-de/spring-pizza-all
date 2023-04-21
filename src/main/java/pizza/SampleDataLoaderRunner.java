package pizza;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Performs loading of sample data after the context has started up.
 * <p>
 * We do not want to use @PostConstruct in any SampleDataLoader implementation itself since this
 * caused timing issues when using the default Spring Boot H2 data source. The data could be loaded
 * but wasn't available afterwards for querying (empty tables).
 */
@Component
@Order(1)
public class SampleDataLoaderRunner implements ApplicationRunner {

    private final SampleDataLoader sampleDataLoader;

    public SampleDataLoaderRunner(@Qualifier("small") SampleDataLoader sampleDataLoader) {
        this.sampleDataLoader = sampleDataLoader;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.sampleDataLoader.run();
    }
}

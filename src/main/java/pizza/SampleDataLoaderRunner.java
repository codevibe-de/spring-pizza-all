package pizza;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoaderRunner implements ApplicationRunner {

    private final SampleDataLoader sampleDataLoader;

    public SampleDataLoaderRunner(@Qualifier("small") SampleDataLoader sampleDataLoader) {
        this.sampleDataLoader = sampleDataLoader;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.sampleDataLoader.run();
    }
}

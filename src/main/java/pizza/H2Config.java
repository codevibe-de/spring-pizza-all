package pizza;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.stream.Stream;

/**
 * Configuration of our external TCP-based H2 Server. Includes fine-tuning of bean creation order.
 */
@Configuration
public class H2Config {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public H2TcpServer h2TcpServer() {
        return new H2TcpServer();
    }

    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("h2TcpServer"));
        };
    }
}

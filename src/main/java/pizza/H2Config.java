package pizza;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

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

    // this BeanFactoryPostProcessor will make the EntityManagerFactory wait for our database to start
    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            try {
                // since EntityManagerFactory is not on classpath in 050-exercise branch we need
                // to work around that using "ResolvableType"
                ResolvableType entityMgrType = ResolvableType.forClass(Class.forName("javax.persistence.EntityManagerFactory"));
                String[] jpa = bf.getBeanNamesForType(entityMgrType);
                Stream.of(jpa)
                        .map(bf::getBeanDefinition)
                        .forEach(it -> it.setDependsOn("h2TcpServer"));
            } catch (ClassNotFoundException e) {
                // this is okay, might not be on classpath in "exercise" branch
            }
        };
    }
}

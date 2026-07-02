package pizza;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "persistence-config.enabled")
public class PersistenceConfig {

//    @Bean
//    public DataSource dataSource() {
//        var ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:tcp://localhost:9092/./pizzadb");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        return ds;
//    }

}

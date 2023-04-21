package pizza;

import org.h2.tools.RunScript;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStreamReader;

/**
 * Runs the database schema script (e.g. table creation) defined in file "/src/main/resources/schema.sql"
 */
@Component
@Order(0)
public class SchemaScriptRunner implements ApplicationRunner {

    private final DataSource dataSource;

    public SchemaScriptRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running schema script");
        var resource = new ClassPathResource("/schema.sql");
        try (var inStream = new InputStreamReader(resource.getInputStream())) {
            RunScript.execute(this.dataSource.getConnection(), inStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

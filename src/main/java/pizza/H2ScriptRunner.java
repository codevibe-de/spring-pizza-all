package pizza;

import org.h2.tools.RunScript;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.InputStreamReader;

@Component
@DependsOn("h2Launcher")
public class H2ScriptRunner implements Runnable {

    private final DataSource dataSource;

    public H2ScriptRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    @Override
    public void run() {
        System.out.println("Running schema script");
        var resource = new ClassPathResource("/schema.sql");
        try (var inStream = new InputStreamReader(resource.getInputStream())) {
            RunScript.execute(this.dataSource.getConnection(), inStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

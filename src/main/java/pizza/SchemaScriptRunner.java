package pizza;

import org.h2.tools.RunScript;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Runs the database schema script (e.g. table creation) defined in file
 * "/src/main/resources/schema.sql"
 */
public class SchemaScriptRunner implements Runnable {

    private final DataSource dataSource;

    public SchemaScriptRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        System.out.println("Running schema script");
        var resourceAsStream = Objects.requireNonNull(
                getClass().getResourceAsStream("/schema.sql"),
                "Resource /schema.sql not found"
        );
        try (var inStream = new InputStreamReader(resourceAsStream)) {
            RunScript.execute(this.dataSource.getConnection(), inStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

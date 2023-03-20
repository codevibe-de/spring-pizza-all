package pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This class is to show that OS environment variables like `TEMP` (at least on Windows) overwrite
 * a value configured in the `application.properties` file.
 * Note that the placeholder is using a default value by adding the ':' at the end.
 */
@Component
public class ConfigOutputDebugRunner implements CommandLineRunner {

    private static final String PROP_NAME = "temp";

    @Value("${" + PROP_NAME + ":}")
    private String injectedValue;

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) {
        System.out.printf("Injected value of property \"%s\" is \"%s\"\n", PROP_NAME, injectedValue);
        System.out.printf("Environment value of property \"%s\" is \"%s\"\n", PROP_NAME, environment.getProperty(PROP_NAME));
    }
}

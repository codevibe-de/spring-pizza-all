package d017_resources;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

public class CreateEnvironment {

    public static void main(String[] args) throws IOException {
        var environment = createStandardEnvironment();
        System.out.println(environment.getProperty("temp"));
        System.out.println(environment.getProperty("username"));
        System.out.println(environment.getProperty("app.my-value"));
    }


    public static StandardEnvironment createStandardEnvironment() throws IOException {
        // create env
        var environment = new StandardEnvironment();
        // load and add properties to env
        var resource = new DefaultResourceLoader().getResource("app.properties");
        var properties = PropertiesLoaderUtils.loadProperties(resource);
        var propertySource = new PropertiesPropertySource("app", properties);
        environment.getPropertySources().addLast(propertySource);
        // done
        return environment;
    }
}

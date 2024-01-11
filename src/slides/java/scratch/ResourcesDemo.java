package scratch;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import static java.lang.System.out;

public class ResourcesDemo {

    public static void main(String[] args) throws IOException {
        Resource fileResource = new FileSystemResource("pom.xml");
        analyseResource(fileResource);

        // absolute path
        Resource classpathResource = new ClassPathResource("/data/stuff.csv");
        analyseResource(classpathResource);
        Resource classpathResource2 = new ClassPathResource("relative.txt", ResourcesDemo.class);
        analyseResource(classpathResource2);

        Resource urlResource = new UrlResource("https://github.com/tauinger-de/training.spring/blob/main/README.md");
        analyseResource(urlResource);
    }

    private static void analyseResource(Resource r) throws IOException {
        out.printf("\nResource '%s'\n", r.getFilename());
        out.printf("  - description = %s\n", r.getDescription());
        out.printf("  - exists = %s\n", r.exists());
        out.printf("  - uri = %s\n", r.getURI());
        out.printf("  - length = %d\n", r.contentLength());
        out.printf("  - last-mod = %s\n",
                Instant.ofEpochMilli(r.lastModified()).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}

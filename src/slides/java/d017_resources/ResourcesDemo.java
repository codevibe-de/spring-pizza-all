package d017_resources;

import org.springframework.core.io.*;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import static java.lang.System.out;

public class ResourcesDemo {

    public static void main(String[] args) throws IOException {
        // FileSystemResource direct instantiation
        Resource fileResource = new FileSystemResource("pom.xml");
        analyseResource(fileResource);

        // ClassPathResource direct instantiation
        // absolute path
        Resource classpathResource = new ClassPathResource("data/stuff.csv");
        analyseResource(classpathResource);
        // relative path
        Resource classpathResource2 = new ClassPathResource("relative.txt", ResourcesDemo.class);
        analyseResource(classpathResource2);

        // UrlResource direct instantiation
        Resource urlResource = new UrlResource("https://github.com/tauinger-de/training.spring/blob/main/README.md");
        analyseResource(urlResource);

        // Generic loading via DefaultResourceLoader:
        Resource r = new DefaultResourceLoader().getResource("file:data/stuff.csv");
        analyseResource(r);
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

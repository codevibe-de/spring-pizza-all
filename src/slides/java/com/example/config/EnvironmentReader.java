package com.example.config;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

@Component
public class EnvironmentReader {

    private final Environment environment;

    public EnvironmentReader(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void readEnvironment() {
        environment.getProperty("app.name");
        environment.getProperty("app.version", "1.0.0");
        environment.getProperty("app.default-location", Point2D.class, new Point2D.Double(0, 0));
        environment.getRequiredProperty("app.port", Integer.class);
    }
}

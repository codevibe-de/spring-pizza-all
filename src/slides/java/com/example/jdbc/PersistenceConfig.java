package com.example.jdbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/db.properties")
public class PersistenceConfig {
}

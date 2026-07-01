package com.example.resources;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

public class ResourceApp {

    public static void main(String[] args) throws Exception {
        Resource resource = new ClassPathResource("beans.xml");

        // alt:
        String text = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        // seit Spring 6.0.5 auch möglich:
        text = resource.getContentAsString(StandardCharsets.UTF_8);
    }

}


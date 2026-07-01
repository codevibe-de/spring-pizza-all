package com.example.resources;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderApp {

    public static void main(String[] args) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        printInfo(resourceLoader.getResource("classpath:/beans.xml")); // exists
        printInfo(resourceLoader.getResource("file:./pom.xml")); // exists

        resourceLoader = new FileSystemResourceLoader();
        printInfo(resourceLoader.getResource("beans.xml")); // does not exist
        printInfo(resourceLoader.getResource("pom.xml")); // exists
    }

    private static void printInfo(Resource r) {
        System.out.println("Resource '" + r.getFilename() + "' exists: " + r.exists());
    }
}

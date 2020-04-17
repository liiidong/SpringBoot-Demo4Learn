package com.enough.demo.warintomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoWarInTomcatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoWarInTomcatApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoWarInTomcatApplication.class);
    }
}

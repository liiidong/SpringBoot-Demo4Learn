package com.enough.demopoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enough.demopoi.*")
public class DemoPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPoiApplication.class, args);
    }

}

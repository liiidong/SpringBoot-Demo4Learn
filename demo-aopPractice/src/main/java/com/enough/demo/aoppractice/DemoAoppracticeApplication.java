package com.enough.demo.aoppractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enough.demo.aoppractice.*")
public class DemoAoppracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAoppracticeApplication.class, args);

    }
}

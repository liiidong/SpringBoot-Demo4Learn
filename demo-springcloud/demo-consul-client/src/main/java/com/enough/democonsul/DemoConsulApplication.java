package com.enough.democonsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoConsulApplication.class, args);
    }

}

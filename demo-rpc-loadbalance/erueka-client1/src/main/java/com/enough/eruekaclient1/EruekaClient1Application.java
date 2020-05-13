package com.enough.eruekaclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EruekaClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(EruekaClient1Application.class, args);
    }

}

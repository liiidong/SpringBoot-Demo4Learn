package com.enough.demo.aoppractice;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enough.demo.aoppractice.*", exclude = EasyPoiAutoConfiguration.class)
public class DemoAoppracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAoppracticeApplication.class, args);

    }
}

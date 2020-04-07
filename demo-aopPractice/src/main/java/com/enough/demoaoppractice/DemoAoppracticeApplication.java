package com.enough.demoaoppractice;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enough.*", exclude = EasyPoiAutoConfiguration.class)
public class DemoAoppracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAoppracticeApplication.class, args);
    }

}

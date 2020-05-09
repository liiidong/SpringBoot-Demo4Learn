package com.enough.demopoi;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enough.demopoi.*", exclude = EasyPoiAutoConfiguration.class)
public class DemoPoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoPoiApplication.class, args);
    }

}

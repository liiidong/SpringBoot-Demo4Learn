package com.enough.serviceconfigmgt;

import cn.afterturn.easypoi.configuration.EasyPoiAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//扫描包
@SpringBootApplication(scanBasePackages = "com.enough.*",exclude = EasyPoiAutoConfiguration.class)
@MapperScan("com.enough.configmanager.dao") //不用再dao或mapper上加@Mapper或者@Repository
public class ServiceConfigmgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConfigmgtApplication.class, args);
    }

}

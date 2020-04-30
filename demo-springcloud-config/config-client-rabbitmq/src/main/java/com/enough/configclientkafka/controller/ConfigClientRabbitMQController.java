package com.enough.configclientkafka.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/29
 */
@RestController
@RequestMapping("/kafka")
@RefreshScope
public class ConfigClientKafkaController {

    @Value("${foo}")
    private String foo;

    @GetMapping("/foo")
    public String getFoo() {
        return foo;
    }
}

package com.enough.eruekaclient1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/13
 */
@RestController
@RequestMapping("/")
@Slf4j
public class EurekClient2Controler {

    @Value("${server.port}")
    private String port;
    @Value("${eureka.instance.hostname}")
    private String hostname;

    @GetMapping("server")
    public String server(){
        return new StringBuilder(hostname).append(port).toString();
    }
}

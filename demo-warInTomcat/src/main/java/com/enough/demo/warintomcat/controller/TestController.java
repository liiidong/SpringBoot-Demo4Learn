package com.enough.demo.warintomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: liiidong
 * @create: 2020/04/08
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World!!";
    }

}

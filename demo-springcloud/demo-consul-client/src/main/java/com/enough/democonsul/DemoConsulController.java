package com.enough.democonsul;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/20
 */
@RestController
@RequestMapping("/consul")
public class DemoConsulController {

    @GetMapping("hi")
    public String hi(){
        return "hi, i am consul";
    }
}

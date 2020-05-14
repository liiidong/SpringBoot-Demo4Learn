package com.enough.rpcbalancedemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: openFengin调用接口
 * @author: lidong
 * @create: 2020/05/14
 */
@FeignClient(name = "eureka-client") //name的值设置 客户端注册在eureka上的名字，参考eureka-clinet*的spring.application.name
public interface FenginService {

    @GetMapping("/eurekaClient/server") //eurekaClient/server：远程服务地址，如果GetMapping不起作用，可以尝试使用RequestMapping
    String getServerUrl();
}

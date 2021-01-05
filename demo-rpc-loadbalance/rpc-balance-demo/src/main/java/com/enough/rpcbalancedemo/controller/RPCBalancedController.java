package com.enough.rpcbalancedemo.controller;

import com.enough.rpcbalancedemo.service.FeignService;
import com.enough.rpcbalancedemo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program  SpringBoot-Demo4Learn
 * @apiNote  RPC-Balanced服务接口
 * @author lidong
 * @since    2020/05/14
 */
@RestController
@RequestMapping("/rpcBalanced")
public class RPCBalancedController {

    @Autowired
    private RestTemplateService restTemplateService;

    @Qualifier("eureka-client")
    @Autowired
    private FeignService feignService;

    @GetMapping("/restTemplate")
    public String RPCWithRestTeplate() {
        return restTemplateService.RPCWithRestTeplate();
    }

    @GetMapping("/restTemplateHystrix")
    public String RPCWithRestTeplateHystrix() {
        return restTemplateService.RPCWithRestTeplateHystrix();
    }

    @GetMapping("/openFeign")
    public String RPCWithOpenFeign() {
        return feignService.getServerUrl();
    }

    @GetMapping("/openFeignHystrix")
    public String RPCWithOpenFeignHystrix() {
        return feignService.getServerUrl();
    }

}

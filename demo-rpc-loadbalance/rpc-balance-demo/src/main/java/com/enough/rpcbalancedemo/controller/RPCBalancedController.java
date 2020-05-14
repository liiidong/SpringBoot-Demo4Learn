package com.enough.rpcbalancedemo.controller;

import com.enough.rpcbalancedemo.service.FenginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: RPC-Balanced服务接口
 * @author: lidong
 * @create: 2020/05/14
 */
@RestController
@RequestMapping("/rpcBalanced")
public class RPCBalancedController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FenginService fenginService;

    @Value("${rpcServices.serviceName}")
    private String serviceName;
    @Value("${rpcServices.contextPath}")
    private String contextPath;
    @Value("${rpcServices.getServerUrl}")
    private String getServerUrl;

    @GetMapping("/restTemplate")
    public String RPCWithRestTeplate() {
        ResponseEntity <String> entity = restTemplate.getForEntity("http://".concat(serviceName).concat(contextPath).concat(getServerUrl), String.class);
        return entity.getBody();
    }

    @GetMapping("/openFengin")
    public String RPCWithOpenFengin() {
        return fenginService.getServerUrl();
    }
}

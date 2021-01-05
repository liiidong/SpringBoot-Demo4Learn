package com.enough.rpcbalancedemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/05/14
 */
@Service
public class RestTemplateService {
    @Value("${rpcServices.serviceName}")
    private String serviceName;
    @Value("${rpcServices.contextPath}")
    private String contextPath;
    @Value("${rpcServices.getServerUrl}")
    private String getServerUrl;
    @Autowired
    private RestTemplate restTemplate;

    public String RPCWithRestTeplate() {
        ResponseEntity <String> entity = restTemplate.getForEntity("http://".concat(serviceName).concat(contextPath).concat(getServerUrl), String.class);
        return entity.getBody();
    }

    @HystrixCommand(fallbackMethod = "getServerError")
    public String RPCWithRestTeplateHystrix() {
        ResponseEntity <String> entity = restTemplate.getForEntity("http://".concat(serviceName).concat(contextPath).concat(getServerUrl), String.class);
        return entity.getBody();
    }

    public String getServerError() {
        return "restTemplateHystrix服务器繁忙！";
    }
}

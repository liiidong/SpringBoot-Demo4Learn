package com.enough.rpcbalancedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启openFengin客户端
@EnableHystrix //RestTemplate开启服务熔断降级
@EnableCircuitBreaker //Feign开启熔断机制
public class RpcBalanceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcBalanceDemoApplication.class, args);
    }

    @Bean
    @LoadBalanced //注入RestTemplate作为RPC调用对象，并开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

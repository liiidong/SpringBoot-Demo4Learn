package com.enough.configmanager.listener;

import com.enough.common.rest.utils.OKHttpUtil;
import com.enough.configmanager.event.ConfigPropertyChangeEvent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 配置改变监听
 * @author: lidong
 * @create: 2020/05/11
 */
@Component
@Slf4j
public class ConfigPropertyChangeListener implements ApplicationListener <ConfigPropertyChangeEvent> {

    /**
     * 配置中心服务刷新配置地址，POST请求，不使用eureka的时候使用
     */
    private static final String URL = "http://127.0.0.1:8769/actuator/bus-refresh";

    @Value("${local-address:empty}")
    private String localAddress;

    @Async
    @Override
    public void onApplicationEvent(ConfigPropertyChangeEvent configPropertyChangeEvent) {
        try {
            refreshWithOKHttp();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 使用OKHttp发送【异步】请求
     */
    private void refreshWithOKHttp() throws InterruptedException {
        OKHttpUtil.postUseOkhttpAsync(URL,"{}");
        //等待请求线程，否则主线程结束无法看到请求结果
        Thread.currentThread().join();
    }
}

package com.enough.configmanager.listener;

import com.enough.configmanager.event.ConfigPropertyChangeEvent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
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

    //@Value("${server.port}")
    //private String serverPort;

    @Async
    @Override
    public void onApplicationEvent(ConfigPropertyChangeEvent configPropertyChangeEvent) {
        //switch (configPropertyChangeEvent.getOperateType()) {
        //case ADD:
        //    break;
        //case UPDATE:
        //    break;
        //case DELETE:
        //    break;
        //}
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
        //1、创建OkHttpClient对象实例
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).build();
        //2、创建Request对象
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder().url(URL).post(requestBody).build();
        //3、执行Request请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.debug("onResponse: " + response.body().string());
            }
        });
        //等待请求线程，否则主线程结束无法看到请求结果
        Thread.currentThread().join();
    }
}

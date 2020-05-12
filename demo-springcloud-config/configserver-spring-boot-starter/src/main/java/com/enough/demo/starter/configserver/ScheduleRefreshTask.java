package com.enough.demo.starter.configserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: config-client
 * @description:
 * @author: lidong
 * @create: 2019/08/16
 */
@EnableScheduling
@Slf4j
//@Component
//@Configuration  根据配置手动注入
public class ScheduleRefreshTask {
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(30);

    private ScheduleConfig scheduleConfig;

    private boolean readyRefresh = false;

    @Autowired
    private RefreshConfig refreshConfig;

    public void setScheduleConfig(ScheduleConfig scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }
    public void setReadyRefresh(boolean readyRefresh){
        this.readyRefresh = readyRefresh;
    }

    public void refershConfig() {
        Runnable runnable = () -> {
            try {
                if (readyRefresh) {
                    refreshConfig.refresh();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                executor.shutdown();
            }
        };
        //延迟一秒，每隔10秒执行
        executor.scheduleWithFixedDelay(runnable, 1, scheduleConfig.getFixedRate(), TimeUnit.SECONDS);
        executor.submit(runnable);
    }

}

package com.supermap.gaf.configserver;

import com.supermap.gaf.rest.utils.OKHttpUtil;
import com.supermap.gaf.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
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
//@Component
//@Configuration
@EnableScheduling
public class ScheduleRefreshTask {
    private static Logger logger = LogUtil.getLocLogger(ScheduleRefreshTask.class);
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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (readyRefresh) {
                        refreshConfig.refresh();
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    executor.shutdown();
                }
            }
        };
        //延迟一秒，每隔10秒执行
        executor.scheduleWithFixedDelay(runnable, 1, scheduleConfig.getFixedRate(), TimeUnit.SECONDS);
        executor.submit(runnable);
        //        Thread thread = new Thread() {
        //            @Override
        //            public void run() {
        //                String rst = "";
        //                FutureTask future = new FutureTask(runnable, rst);
        //                try {
        //                    //等待10分钟
        //                    future.get(600, TimeUnit.SECONDS);
        //                } catch (Exception e) {
        //                    logger.error("k8s部署数据库服务超时");
        //                    executor.shutdown();
        //                }
        //                executor.submit(future);
        //            }
        //        };
        //        thread.start();
    }

}

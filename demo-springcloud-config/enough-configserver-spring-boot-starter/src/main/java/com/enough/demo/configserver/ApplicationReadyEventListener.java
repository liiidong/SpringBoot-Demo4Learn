package com.supermap.gaf.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author <a href="mailto:wenyuanwu@gtmap.cn">wenyuanwu</a>
 * @version 1.0 2019-9-5
 * @description 监听程序是否懂成功
 */
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScheduleRefreshTask scheduleRefreshTask;

    @Autowired
    private RefreshConfig refreshConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        scheduleRefreshTask.setReadyRefresh(true);
        refreshConfig.setEnable(true);
    }
}

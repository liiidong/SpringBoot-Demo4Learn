package com.enough.demo.starter.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationReadyEventListener implements ApplicationListener <ApplicationReadyEvent> {

    @Autowired
    private com.enough.demo.starter.configserver.ScheduleRefreshTask scheduleRefreshTask;

    @Autowired
    private com.enough.demo.starter.configserver.RefreshConfig refreshConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        scheduleRefreshTask.setReadyRefresh(true);
        refreshConfig.setEnable(true);
    }
}

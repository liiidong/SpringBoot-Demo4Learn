package com.supermap.gaf.configserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: gaf-commons-modules
 * @description:
 * @author: lidong
 * @create: 2019/08/21
 */
@Configuration
@ConditionalOnProperty(name="configserver.enable", havingValue="true", matchIfMissing = true)
@EnableConfigurationProperties({ ScheduleConfig.class })
public class ScheduleConfiguration {

    @Autowired
    private ScheduleConfig scheduleConfig;

    /**
     * 创建刷新配置任务
     * 
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "configserver.refreshconfig.enable", havingValue = "true")
    public ScheduleRefreshTask scheduleRefreshTask() {
        ScheduleRefreshTask scheduleRefreshTask = new ScheduleRefreshTask();
        scheduleRefreshTask.setScheduleConfig(scheduleConfig);
        scheduleRefreshTask.refershConfig();
        return scheduleRefreshTask;
    }

    @Bean
    @ConditionalOnProperty(name = "configserver.refreshconfig.enable", havingValue = "true")
    public ApplicationReadyEventListener applicationReadyEventListener(){
        return new ApplicationReadyEventListener();
    }

    @Bean
    public RefreshConfig refreshConfig() {
        return new RefreshConfig();
    }
}

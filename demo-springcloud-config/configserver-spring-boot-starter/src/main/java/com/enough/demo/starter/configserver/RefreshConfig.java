package com.enough.demo.starter.configserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class RefreshConfig {

    private boolean enable = false;

    @Autowired(required = false)
    private ContextRefresher contextRefresher;

    @Autowired(required = false)
    private ApplicationContext applicationContext;

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public RefreshConfig(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    public RefreshConfig() {

    }

    public Collection <String> refresh() {
        if (enable) {
            Set <String> keys = contextRefresher.refresh();
            log.info("配置刷新:{}", keys);
            return keys;
        } else {
            return new HashSet <>();
        }
    }

}

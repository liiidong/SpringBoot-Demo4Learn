package com.supermap.gaf.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:wenyuanwu@gtmap.cn">wenyuanwu</a>
 * @version 1.0 2019-9-5
 * @description 刷新配置方法
 */
public class RefreshConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private boolean enable = false;

    @Autowired
    private ContextRefresher contextRefresher;

    @Autowired
    private ApplicationContext applicationContext;



    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public RefreshConfig(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    public RefreshConfig() {

    }

    public Collection<String> refresh() {
        if (enable) {
            Set<String> keys = contextRefresher.refresh();
            logger.info("配置刷新:{}",keys);
            return keys;
        } else {
            return new HashSet<>();
        }
    }

}

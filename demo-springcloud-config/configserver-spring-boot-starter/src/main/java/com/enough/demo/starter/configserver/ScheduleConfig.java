package com.enough.demo.starter.configserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: gaf-commons-modules
 * @description:
 * @author: lidong
 * @create: 2019/08/21
 */
@Configuration
@ConfigurationProperties(prefix = "configserver.refreshconfig")
public class ScheduleConfig {
    private boolean enable;
    /**
     * # 刷新间隔，尽量不要太短 最好>10，避免服务重新注册等问题导致拒绝刷新链接
     */
    private int fixedRate;
    /**
     * ip+port地址
     */
    private String address;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(int fixedRate) {
        this.fixedRate = fixedRate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

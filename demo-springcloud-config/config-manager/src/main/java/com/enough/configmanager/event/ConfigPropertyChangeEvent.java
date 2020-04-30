package com.enough.configmanager.event;

import com.enough.configmanager.dto.ConfigPropertyDTO;
import com.google.common.collect.Lists;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/30
 */
public class ConfigPropertyChangeEvent extends ApplicationEvent {

    private List <ConfigPropertyDTO> configProperties;
    private OperateType operateType;

    public ConfigPropertyChangeEvent(Object source) {
        super(source);
    }

    public ConfigPropertyChangeEvent(Object source, OperateType operateType, ConfigPropertyDTO configPropertyDTO) {
        super(source);
        this.setConfigProperties(Lists.newArrayList(configPropertyDTO));
        this.setOperateType(operateType);
    }

    public ConfigPropertyChangeEvent(Object source, OperateType operateType, List <ConfigPropertyDTO> configProperties) {
        super(source);
        this.setConfigProperties(configProperties);
        this.setOperateType(operateType);
    }

    public List <ConfigPropertyDTO> getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(List <ConfigPropertyDTO> configProperties) {
        this.configProperties = configProperties;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public enum OperateType {
        ADD, DELETE, UPDATE
    }
}

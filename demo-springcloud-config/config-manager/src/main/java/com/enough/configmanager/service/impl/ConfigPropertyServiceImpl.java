package com.enough.configmanager.service.impl;

import com.enough.common.model.CommonBuilder;
import com.enough.configmanager.dao.ConfigPropertyMapper;
import com.enough.configmanager.dto.ConfigPropertyDTO;
import com.enough.configmanager.event.ConfigPropertyChangeEvent;
import com.enough.configmanager.service.ConfigPropertyService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/30
 */
@Service
public class ConfigPropertyServiceImpl implements ConfigPropertyService {

    @Autowired
    private ConfigPropertyMapper configPropertyMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean addConfigProperty(ConfigPropertyDTO configPropertyDTO) {
        return configPropertyMapper.insertSelective(configPropertyDTO) > 0;
    }

    @Override
    public boolean addConfigProperties(List <ConfigPropertyDTO> queryConfigProperties) {
        return configPropertyMapper.addConfigProperties(queryConfigProperties) > 0;
    }

    /**
     * 获取所有的配置信息
     *
     * @return
     */
    @Override
    public List <ConfigPropertyDTO> getAllConfigProperties() {
        return configPropertyMapper.getAllConfigProperties();
    }

    /**
     * 根据条件查询：application、label、profile等
     *
     * @param searchParam
     * @return
     */
    @Override
    public List <ConfigPropertyDTO> queryConfigProperties(ConfigPropertyDTO searchParam) {
        return configPropertyMapper.queryConfigProperties(searchParam);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public ConfigPropertyDTO getConfigProperty(String id) {
        return configPropertyMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteConfigProperty(String id) {
        boolean rst = configPropertyMapper.deleteByPrimaryKey(id) > 0;
        if (rst) {
            applicationContext.publishEvent(new ConfigPropertyChangeEvent(this, ConfigPropertyChangeEvent.OperateType.DELETE,
                    CommonBuilder.of(ConfigPropertyDTO::new).with(ConfigPropertyDTO::setId, id).build()));
        }
        return rst;
    }

    /**
     * 根据application批量删除
     *
     * @param application
     * @return
     */
    @Override
    public boolean deleteConfigProperties(String application) {
        boolean rst = configPropertyMapper.deleteConfigProperties(application) > 0;
        if (rst) {
            applicationContext.publishEvent(new ConfigPropertyChangeEvent(this, ConfigPropertyChangeEvent.OperateType.DELETE,
                    CommonBuilder.of(ConfigPropertyDTO::new).with(ConfigPropertyDTO::setApplication, application).build()));
        }
        return rst;
    }

    /**
     * 批量更新
     *
     * @param configPropertyDTOS
     * @return
     */
    @Override
    public boolean updateConfigProperties(List <ConfigPropertyDTO> configPropertyDTOS) {
        boolean rst = configPropertyMapper.updateConfigProperties(configPropertyDTOS) > 0;
        if (rst) {
            applicationContext.publishEvent(new ConfigPropertyChangeEvent(this, ConfigPropertyChangeEvent.OperateType.UPDATE, configPropertyDTOS));
        }
        return rst;
    }

    @Override
    public void refreshTest() {
        applicationContext.publishEvent(new ConfigPropertyChangeEvent(this, ConfigPropertyChangeEvent.OperateType.UPDATE, Lists.newArrayList()));
    }
}

package com.enough.configmanager.service;

import com.enough.configmanager.dto.ConfigPropertyDTO;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/30
 */
public interface ConfigPropertyService {

    boolean addConfigProperty(ConfigPropertyDTO configPropertyDTO);

    boolean addConfigProperties(List <ConfigPropertyDTO> queryConfigProperties);

    /**
     * 获取所有的配置信息
     *
     * @return
     */
    List <ConfigPropertyDTO> getAllConfigProperties();

    /**
     * 根据条件查询：application、label、profile等
     *
     * @param searchParam
     * @return
     */
    List <ConfigPropertyDTO> queryConfigProperties(ConfigPropertyDTO searchParam);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ConfigPropertyDTO getConfigProperty(String id);

    /**
     * 根据ID删除
     *
     * @return
     */
    boolean deleteConfigProperty(String id);

    /**
     * 根据application批量删除
     *
     * @param application
     * @return
     */
    boolean deleteConfigProperties(String application);

    /**
     * 批量更新
     *
     * @param configPropertyDTOS
     * @return
     */
    boolean updateConfigProperties(List <ConfigPropertyDTO> configPropertyDTOS);

    void refreshTest();
}

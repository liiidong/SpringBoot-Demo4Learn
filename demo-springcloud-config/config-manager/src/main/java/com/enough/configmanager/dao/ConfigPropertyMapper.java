package com.enough.configmanager.dao;

import com.enough.configmanager.dto.ConfigPropertyDTO;

import java.util.List;

public interface ConfigPropertyMapper {
    int deleteByPrimaryKey(String id);

    int insert(ConfigPropertyDTO record);

    int insertSelective(ConfigPropertyDTO record);

    ConfigPropertyDTO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ConfigPropertyDTO record);

    int updateByPrimaryKey(ConfigPropertyDTO record);

    List<ConfigPropertyDTO> getAllConfigProperties();

    List<ConfigPropertyDTO> queryConfigProperties(ConfigPropertyDTO searchParam);

    int deleteConfigProperties(String application);

    int updateConfigProperties(List<ConfigPropertyDTO> configPropertyDTOS);

    int addConfigProperties(List<ConfigPropertyDTO> queryConfigProperties);
}
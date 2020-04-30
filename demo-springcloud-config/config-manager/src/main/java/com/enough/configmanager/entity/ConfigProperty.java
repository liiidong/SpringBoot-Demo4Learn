package com.enough.configmanager.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * config_properties
 * @author 
 */
@Data
public class ConfigProperty implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * key
     */
    private String propertyKey;

    /**
     * 值
     */
    private String propertyValue;

    /**
     * 应用名
     */
    private String application;

    /**
     * 激活的文件
     */
    private String profile;

    /**
     * 标签
     */
    private String label;

    /**
     * 用户ID预留字段
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    private static final long serialVersionUID = 1L;
}
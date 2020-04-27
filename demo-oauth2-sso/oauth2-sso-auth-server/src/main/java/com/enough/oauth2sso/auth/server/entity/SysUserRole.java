package com.enough.oauth2sso.auth.server.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * sys_permission
 * @author 
 */
@Table(name="sys_permission")
@Data
public class SysUserRole implements Serializable {
    @Id
    @GeneratedValue
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 资源类型（1：菜单，2：按钮，3：操作）
     */
    @NotEmpty
    private Byte type;

    /**
     * 资源名称
     */
    @NotEmpty
    private String name;

    /**
     * 资源标识（或者叫权限字符串）
     */
    @NotEmpty
    private String code;

    /**
     * 资源URI
     */
    private String uri;

    /**
     * 序号
     */
    private Integer seq;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
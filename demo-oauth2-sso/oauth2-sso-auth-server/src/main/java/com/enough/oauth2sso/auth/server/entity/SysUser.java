package com.enough.oauth2sso.auth.server.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * sys_user
 * @author 
 */
@Table(name="sys_user")
@Data
public class SysUser implements Serializable {
    @Id
    @GeneratedValue
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 账号
     */
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 昵称
     */
    @NotEmpty
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态（0：锁定，1：解锁）
     */
    @NotEmpty
    private Byte status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
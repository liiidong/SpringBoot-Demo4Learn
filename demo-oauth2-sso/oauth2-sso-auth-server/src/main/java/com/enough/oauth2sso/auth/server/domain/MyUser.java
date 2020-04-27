package com.enough.oauth2sso.auth.server.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @program: SpringBoot-Demo4Learn
 * @description: 自定义用户信息，需要继承security包下的user
 * @author: lidong
 * @create: 2020/04/26
 */
@Data
public class MyUser extends User {
    /**
     * 自定义业务相关的字段:举个例子，部门ID
     */
    private Integer departmentId;
    /**
     * 自定义业务相关的字段：增加一个mobile表示手机号
     */
    private String mobile;

    public MyUser(String username, String password, Collection <? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
            Collection <? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}

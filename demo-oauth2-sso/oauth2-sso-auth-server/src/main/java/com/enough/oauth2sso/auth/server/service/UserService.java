package com.enough.oauth2sso.auth.server.service;

import com.enough.oauth2sso.auth.server.entity.SysUser;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/26
 */
public interface UserService {
    SysUser getByUsername(String username);
}

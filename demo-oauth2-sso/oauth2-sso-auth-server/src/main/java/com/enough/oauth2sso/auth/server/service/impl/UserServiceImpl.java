package com.enough.oauth2sso.auth.server.service.impl;

import com.enough.oauth2sso.auth.server.entity.SysUser;
import com.enough.oauth2sso.auth.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/26
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public SysUser getByUsername(String username) {
        return null;
    }
}

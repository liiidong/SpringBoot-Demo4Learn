package com.enough.oauth2sso.auth.server.service.impl;

import com.enough.oauth2sso.auth.server.entity.SysPermission;
import com.enough.oauth2sso.auth.server.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/26
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List <SysPermission> findByUserId(Integer id) {
        return null;
    }
}

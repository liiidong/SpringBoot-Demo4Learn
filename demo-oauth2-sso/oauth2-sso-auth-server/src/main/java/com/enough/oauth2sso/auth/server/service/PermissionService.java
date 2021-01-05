package com.enough.oauth2sso.auth.server.service;

import com.enough.oauth2sso.auth.server.entity.SysPermission;

import java.util.List;

/**
 * @program: SpringBoot-Demo4Learn
 * @description:
 * @author: lidong
 * @create: 2020/04/26
 */
public interface PermissionService {
    List <SysPermission> findByUserId(Integer id);
}

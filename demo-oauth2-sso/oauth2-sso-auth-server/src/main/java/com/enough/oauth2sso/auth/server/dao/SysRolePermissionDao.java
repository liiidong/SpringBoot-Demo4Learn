package com.enough.oauth2sso.auth.server.dao;

import com.enough.oauth2sso.auth.server.entity.SysRolePermission;

public interface SysRolePermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}
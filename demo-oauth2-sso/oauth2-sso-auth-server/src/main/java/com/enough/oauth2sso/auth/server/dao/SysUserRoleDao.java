package com.enough.oauth2sso.auth.server.dao;

import com.enough.oauth2sso.auth.server.entity.SysUserRole;

public interface SysUserRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}
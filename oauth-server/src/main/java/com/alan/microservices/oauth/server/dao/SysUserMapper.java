package com.alan.microservices.oauth.server.dao;

import com.alan.microservices.commons.account.domain.SysRole;
import com.alan.microservices.commons.account.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    List<String> getPermsByUsername(String username);

    List<SysRole> getRolesByUsername(String username);

    SysUser getByUsername(String username);
}
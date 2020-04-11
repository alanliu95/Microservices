package com.alan.microservices.account.dao;

import com.alan.microservices.account.model.SysRole;
import com.alan.microservices.account.model.SysUser;
import com.alan.microservices.account.model.SysUserRole;
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
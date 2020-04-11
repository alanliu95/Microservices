package com.alan.microservices.account.dao;

import com.alan.microservices.account.model.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    List<SysRolePermission> selectAll();

    int updateByPrimaryKey(SysRolePermission record);
}
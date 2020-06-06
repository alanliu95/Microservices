package com.alan.mbg.dao;

import com.alan.mbg.model.SysRolePermission;
import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    List<SysRolePermission> selectAll();

    int updateByPrimaryKey(SysRolePermission record);
}
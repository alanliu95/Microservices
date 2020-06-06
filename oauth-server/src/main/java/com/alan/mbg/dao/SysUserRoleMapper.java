package com.alan.mbg.dao;

import com.alan.mbg.model.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
}
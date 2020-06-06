package com.alan.mbg.dao;

import com.alan.mbg.model.SysPermission;
import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);
}
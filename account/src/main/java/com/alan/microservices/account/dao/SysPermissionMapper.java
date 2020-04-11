package com.alan.microservices.account.dao;

import com.alan.microservices.account.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);
}
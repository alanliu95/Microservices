package com.alan.microservices.account.dao;

import com.alan.microservices.account.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
}
package com.alan.microservices.asset.dao;

import com.alan.microservices.commons.asset.domain.Site;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SiteDao {

    @Select("select * from site order by id")
    List<Site> getAll();

    @Select("select * from site where id=#{id} ")
    Site getById(Long id);
}

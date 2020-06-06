package com.alan.microservices.invest.dao;

import com.alan.microservices.invest.model.InvestPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface InvestPointMapper {
    @Select("select * from invest_point where site_id=#{siteId}")
    List<InvestPoint> selectBySiteId(long siteId);
}
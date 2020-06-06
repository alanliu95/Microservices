package com.alan.microservices.invest.dao;

import com.alan.microservices.invest.model.InvestContaminant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface InvestContaminantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestContaminant record);

    InvestContaminant selectByPrimaryKey(Long id);

    List<InvestContaminant> selectAll();

    int updateByPrimaryKey(InvestContaminant record);
}
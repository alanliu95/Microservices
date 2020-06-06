package com.alan.mbg.dao;

import com.alan.mbg.model.InvestContaminant;
import java.util.List;

public interface InvestContaminantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestContaminant record);

    InvestContaminant selectByPrimaryKey(Long id);

    List<InvestContaminant> selectAll();

    int updateByPrimaryKey(InvestContaminant record);
}
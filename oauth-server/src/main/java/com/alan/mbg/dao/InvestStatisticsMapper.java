package com.alan.mbg.dao;

import com.alan.mbg.model.InvestStatistics;
import java.util.List;

public interface InvestStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestStatistics record);

    InvestStatistics selectByPrimaryKey(Long id);

    List<InvestStatistics> selectAll();

    int updateByPrimaryKey(InvestStatistics record);
}
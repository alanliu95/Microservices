package com.alan.mbg.dao;

import com.alan.mbg.model.InvestPoint;
import java.util.List;

public interface InvestPointMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestPoint record);

    InvestPoint selectByPrimaryKey(Long id);

    List<InvestPoint> selectAll();

    int updateByPrimaryKey(InvestPoint record);
}
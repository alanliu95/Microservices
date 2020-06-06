package com.alan.mbg.dao;

import com.alan.mbg.model.InvestAssessment;
import java.util.List;

public interface InvestAssessmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestAssessment record);

    InvestAssessment selectByPrimaryKey(Long id);

    List<InvestAssessment> selectAll();

    int updateByPrimaryKey(InvestAssessment record);
}
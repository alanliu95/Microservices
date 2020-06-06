package com.alan.microservices.invest.service;

import com.alan.microservices.invest.dao.InvestPointMapper;
import com.alan.microservices.invest.model.InvestPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PointService {
    @Autowired
    InvestPointMapper pointMapper;

    public List<InvestPoint> getAll(long siteId){
        return pointMapper.selectBySiteId(siteId);
    }
}

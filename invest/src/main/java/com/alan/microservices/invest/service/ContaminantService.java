package com.alan.microservices.invest.service;

import com.alan.microservices.invest.dao.InvestContaminantMapper;
import com.alan.microservices.invest.model.InvestContaminant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaminantService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    InvestContaminantMapper contaminantMapper;
    void selectAll() throws JsonProcessingException {
        List<InvestContaminant> contaminants=contaminantMapper.selectAll();
        System.out.println(objectMapper.writeValueAsString(contaminants));
    }
}

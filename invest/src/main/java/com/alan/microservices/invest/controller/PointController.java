package com.alan.microservices.invest.controller;

import com.alan.microservices.commons.CodeMsg;
import com.alan.microservices.commons.Result;
import com.alan.microservices.invest.model.InvestPoint;
import com.alan.microservices.invest.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PointController {
    @Autowired
    PointService pointService;
    @GetMapping("/points")
    public Result<List<InvestPoint>> getAll(@RequestParam long siteId){
        List<InvestPoint> points=pointService.getAll(siteId);
        if(points!=null) return Result.success(points);
        return Result.error(new CodeMsg(500,"查询失败"));
    }
}

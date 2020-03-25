package com.alan.microservices.commons.asset.service;

import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Site;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * 正常工作依赖于：eureka-client openFeign
 */

@FeignClient(name = "asset-service")
@RequestMapping("/sites")
public interface SiteFeignSvc {
    @RequestMapping()
    Result<ArrayList<Site>> getAll();

    @RequestMapping("/{id}")
    Result<Site> getById(@PathVariable("id") Long id);
}

//@FeignClient(name = "asset-service")
//@RequestMapping("/sites")
//public interface SiteFeignSvc {
//    @RequestMapping()
//    List<Site> getAll();
//
//    @RequestMapping("/{id}")
//    Site getById(@PathVariable("id") Long id);
//}
package com.alan.microservices.event.handler.controller;

import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.domain.Site;
//import com.alan.microservices.event.handler.service.SiteFeignSvc;
import com.alan.microservices.commons.asset.service.SiteFeignSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/siteInfo")
public class SiteInfoController {
//    @Autowired
//    SiteFeignSvc siteFeignSvc;
//
//    @RequestMapping("/{id}")
//    Site getById(@PathVariable("id") Long id) {
//        return siteFeignSvc.getById(id);
//    }
}

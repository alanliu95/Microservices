package com.alan.microservices.asset.controller;

import com.alan.microservices.asset.service.SiteService;
import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sites")
public class SiteController {
    @Autowired
    SiteService siteService;

    @RequestMapping(method = RequestMethod.GET)
    Result<List<Site>> getAll() {
        List<Site> sites = siteService.getAll();
        return Result.success(sites);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Site getDevById(@PathVariable(value = "id") Long id) {
        Site site = siteService.getById(id);
        return site;
    }

    @RequestMapping(path = "/{id}/detail", method = RequestMethod.GET)
    Site getDevDetail(@PathVariable(value = "id") Long id) {
        Site site = siteService.getById(id);
        return site;
    }
}

//@RestController
////@CrossOrigin
//@RequestMapping("/sites")
//public class SiteController {
//    @Autowired
//    SiteService siteService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    List<Site> getAll() {
//        List<Site> sites = siteService.getAll();
//        return sites;
//    }
//
//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    Site getDevById(@PathVariable(value = "id") Long id) {
//        Site site = siteService.getById(id);
//        return site;
//    }
//}

//@RestController
//@RequestMapping("/devices")
//public class DeviceController {
//    @Autowired
//    DeviceService deviceService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    Result<List<Site>> getAllDevices() {
//        List<Site> devices = deviceService.getAllDevices();
//        return devices != null ? Result.success(devices) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//
//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    Result<Site> getDevById(@PathVariable(value = "id") Integer id) {
//        Site device = deviceService.getById(id);
//        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//    @RequestMapping(path = "/token/{token}", method = RequestMethod.GET)
//    Result<Site> getDevByToken( @PathVariable String token) {
//        Site device = deviceService.getByToken(token);
//        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//}

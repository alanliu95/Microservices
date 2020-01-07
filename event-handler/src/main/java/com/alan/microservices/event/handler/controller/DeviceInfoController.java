package com.alan.microservices.event.handler.controller;

import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.service.DevFeignSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devInfo")
public class DeviceInfoController {
    @Autowired
    DevFeignSvc devFeignSvc;

    @RequestMapping("/token/{token}")
    Result<Device> getByToken(@PathVariable("token") String token) {
        return devFeignSvc.getByToken(token);
    }

    @RequestMapping("/{id}")
    Result<Device> getByToken(@PathVariable("id") Long id) {
        return devFeignSvc.getById(id);
    }
}

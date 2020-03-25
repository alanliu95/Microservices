package com.alan.microservices.commons.asset.service;

import com.alan.microservices.commons.Result;
import com.alan.microservices.commons.asset.domain.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@FeignClient(name = "asset-service")
@RequestMapping("/devices")
public interface DevFeignSvc {
    @RequestMapping("/token/{token}")
    Result<Device> getByToken(@PathVariable("token") String token);

    @RequestMapping()
    Result<ArrayList<Device>> getAllDevices();

    @RequestMapping("/{id}")
    Result<Device> getById(@PathVariable("id") Long id);
}

//@FeignClient(name = "asset-service")
//@RequestMapping("/devices")
//public interface DevFeignSvc {
//    @RequestMapping("/token/{token}")
//    Device getByToken(@PathVariable("token") String token);
//
//    @RequestMapping()
//    List<Device> getAllDevices();
//
//    @RequestMapping("/{id}")
//    Device getById(@PathVariable("id") Long id);
//}

package com.alan.microservices.commons.account.service;

import com.alan.microservices.commons.account.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "account-service")
@RequestMapping("/user")
public interface UserFeignSvc {
    @GetMapping("")
    SysUser getByUsername(@RequestParam("username") String username);
    @GetMapping("/perm")
    List<String> getPermByUsername(@RequestParam("username") String username);
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

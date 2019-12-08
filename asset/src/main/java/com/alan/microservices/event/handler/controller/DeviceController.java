package com.alan.microservices.event.handler.controller;

import com.alan.microservices.event.handler.domain.Device;
import com.alan.microservices.event.handler.service.DeviceService;
import com.alan.microservices.commons.CodeMsg;
import com.alan.microservices.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    Result<Device> getDev(@RequestParam("token") String token) {
        Device device = deviceService.getByToken(token);
        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
    }
}

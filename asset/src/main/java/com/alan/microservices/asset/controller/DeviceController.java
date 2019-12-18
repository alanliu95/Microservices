package com.alan.microservices.asset.controller;

import com.alan.microservices.asset.service.DeviceService;
import com.alan.microservices.commons.asset.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    List<Device> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return devices;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Device getDevById(@PathVariable(value = "id") Long id) {
        Device device = deviceService.getById(id);
        return device;
    }

    @RequestMapping(path = "/token/{token}", method = RequestMethod.GET)
    Device getDevByToken(@PathVariable String token) {
        Device device = deviceService.getByToken(token);
        return device;
    }
}

//@RestController
//@RequestMapping("/devices")
//public class DeviceController {
//    @Autowired
//    DeviceService deviceService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    Result<List<Device>> getAllDevices() {
//        List<Device> devices = deviceService.getAllDevices();
//        return devices != null ? Result.success(devices) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//
//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    Result<Device> getDevById(@PathVariable(value = "id") Integer id) {
//        Device device = deviceService.getById(id);
//        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//    @RequestMapping(path = "/token/{token}", method = RequestMethod.GET)
//    Result<Device> getDevByToken( @PathVariable String token) {
//        Device device = deviceService.getByToken(token);
//        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
//    }
//}

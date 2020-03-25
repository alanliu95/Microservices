package com.alan.microservices.asset.controller;

import com.alan.microservices.asset.service.DeviceService;
import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.domain.DeviceDetail;
import com.alan.microservices.commons.CodeMsg;
import com.alan.microservices.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
//@RequestMapping("/devices")
////@CrossOrigin
//public class DeviceController {
//    @Autowired
//    DeviceService deviceService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    List<Device> getAllDevices() {
//        List<Device> devices = deviceService.getAllDevices();
//        return devices;
//    }
//
//    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
//    Device getDevById(@PathVariable(value = "id") Long id) {
//        Device device = deviceService.getById(id);
//        return device;
//    }
//
//    @RequestMapping(path = "/token/{token}", method = RequestMethod.GET)
//    Device getDevByToken(@PathVariable String token) {
//        Device device = deviceService.getByToken(token);
//        return device;
//    }
//}

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    Result<List<Device>> getAllDevices(@RequestParam(name = "siteId", defaultValue = "0") long siteId) {
        List<Device> devices = null;
        if (siteId == 0)
            devices = deviceService.getAllDevices();
        else
            devices = deviceService.getDevicesBySiteId(siteId);
        return devices != null ? Result.success(devices) : Result.error(CodeMsg.DEV_NOT_EXIST);
    }

    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    Result<List<DeviceDetail>> getAllDevicesDetail(@RequestParam(name = "siteId", defaultValue = "0") long siteId) {
        List<DeviceDetail> deviceDetail = null;
//        if(siteId==0)
//            deviceDetail= deviceService.getAllDevices();
//        else
        deviceDetail = deviceService.getDevicesDetailBySiteId(siteId);
        return deviceDetail != null ? Result.success(deviceDetail) : Result.error(CodeMsg.DEV_NOT_EXIST);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Result<Device> getDevById(@PathVariable Long id) {
        Device device = deviceService.getById(id);
        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
    }

    @RequestMapping(path = "/token/{token}", method = RequestMethod.GET)
    Result<Device> getDevByToken(@PathVariable String token) {
        Device device = deviceService.getByToken(token);
        return device != null ? Result.success(device) : Result.error(CodeMsg.DEV_NOT_EXIST);
    }
}

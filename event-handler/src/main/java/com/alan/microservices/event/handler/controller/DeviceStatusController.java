package com.alan.microservices.event.handler.controller;

import com.alan.microservices.commons.Result;
import com.alan.microservices.event.handler.etl.status.DevStatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class DeviceStatusController {
    @Autowired
    DevStatusHandler devStatusHandler;

    @RequestMapping("/status")
    ConcurrentHashMap<Long, ConcurrentHashMap<Long, Boolean>> status() {
        return devStatusHandler.getStatusTable();
    }

    @RequestMapping("/online")
    Result<ConcurrentHashMap<Long, Boolean>> targetSiteOnline(@RequestParam("siteId") long siteId) {
        return Result.success(devStatusHandler.getStatusTable().get(siteId));
    }
}

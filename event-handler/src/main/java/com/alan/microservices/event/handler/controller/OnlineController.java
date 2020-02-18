package com.alan.microservices.event.handler.controller;

import com.alan.microservices.event.handler.service.DeviceOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineController {
    @Autowired
    DeviceOnlineService onlineService;
    @RequestMapping("/devOnline")
    public Long online(@RequestParam("devId") Long devId){
        return onlineService.query(DeviceOnlineService.ONLINE_STORE,devId);
    }
    @RequestMapping("/devOnlineAmount")
    public Long onlineAmount(@RequestParam("siteId") Long siteId){
        return onlineService.query(DeviceOnlineService.ONLINE_AMOUNT_STORE,siteId);
    }
}

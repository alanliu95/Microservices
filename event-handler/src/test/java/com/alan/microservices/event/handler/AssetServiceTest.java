package com.alan.microservices.event.handler;

import com.alan.microservices.commons.asset.service.DevFeignSvc;
import com.alan.microservices.commons.asset.service.SiteFeignSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetServiceTest {
    @Autowired
    DevFeignSvc devFeignSvc;
    @Autowired
    SiteFeignSvc siteFeignSvc;

    @Test
    public void deviceAllTest() {
        System.out.println(devFeignSvc.getAllDevices());
    }

    @Test
    public void deviceTokenTest() {
        System.out.println(devFeignSvc.getByToken("ubuntu_14th"));
    }

    @Test
    public void deviceIdTest() {
        System.out.println(devFeignSvc.getById(1L));
    }

    @Test
    public void siteIdTest() {
        System.out.println(siteFeignSvc.getById(1L));
    }
}

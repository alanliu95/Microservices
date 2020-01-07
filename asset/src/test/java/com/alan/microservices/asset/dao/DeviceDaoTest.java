package com.alan.microservices.asset.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDaoTest {
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void joinTest() throws JsonProcessingException {
//        System.out.println(objectMapper.writeValueAsString(deviceDao.getDevicesDetailBySiteId(1L)));
        System.out.println(deviceDao.getDevicesDetailBySiteId(1L));
    }

}

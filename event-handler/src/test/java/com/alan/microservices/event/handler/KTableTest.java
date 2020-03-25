package com.alan.microservices.event.handler;

import com.alan.microservices.event.handler.service.DeviceOnlineService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KTableTest {
    @Autowired
    DeviceOnlineService onlineService;
//    @Test
//    public void query(){
//        System.out.println(onlineService.query(1L));
//    }
}

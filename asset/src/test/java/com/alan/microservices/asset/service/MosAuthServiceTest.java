package com.alan.microservices.asset.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MosAuthServiceTest {
    @Autowired
    MosAuthService mosAuthService;
    @Test
    public void access(){
        boolean bool=mosAuthService.authorize("mk60","/site/status",1);
        Assert.assertEquals(bool,true);
    }
}

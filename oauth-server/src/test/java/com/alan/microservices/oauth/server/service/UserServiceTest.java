package com.alan.microservices.oauth.server.service;

import com.alan.microservices.oauth.server.dao.UserMapper;
import com.alan.microservices.oauth.server.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;
    @Test
    public void createUser(){
        User user =new User();
        user.setUsername("alan");
        user.setPassword("alan");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userService.createUser(user);
    }
    @Test
    public void match(){
        assertTrue("匹配成功",
                passwordEncoder.matches("alan",
                        userMapper.selectByPrimaryKey(38L).getPassword()));
    }

}

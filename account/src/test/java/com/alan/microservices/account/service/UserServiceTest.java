package com.alan.microservices.account.service;

import com.alan.microservices.account.dao.SysUserMapper;
import com.alan.microservices.account.model.SysRole;
import com.alan.microservices.account.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    SysUserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void getRoles(){
        List<SysRole> roles=userMapper.getRolesByUsername("admin");
        System.out.println(roles);
    }
    @Test
    public void getPerms(){
        List<String> perms=userService.getPermsAndRolesByUserName("admin");
        System.out.println(perms);
    }
    @Test
    public void getById(){
         SysUser user =userService.getById(37L);
        System.out.println(user);
    }


    @Test
    public void createUser(){
        SysUser user =new SysUser();
        user.setUsername("user");
        user.setPassword("password");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        assertTrue("创建用户失败",userService.createUser(user)>0);
    }
    @Test
    public void match(){
        assertTrue("匹配失败",
                passwordEncoder.matches("password",
                        userMapper.getByUsername("user").getPassword()));
    }
}

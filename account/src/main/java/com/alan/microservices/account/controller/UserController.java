package com.alan.microservices.account.controller;

import com.alan.microservices.account.domain.User;
import com.alan.microservices.account.service.UserService;
import com.alan.microservices.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/{userId}")
    User queryById(@PathVariable("userId") long userId){
        return userService.getById(userId);
    }
    /*
   'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  }
     */

    @RequestMapping("/info")
    Result<Map> info() {
        Map<String, Object> info = new HashMap();
        info.put("roles", new String[]{"admin"});
        info.put("introduction", "I am a super administrator");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "alan");
        return Result.success(info);

    }
}

package com.alan.microservices.account.controller;

import com.alan.microservices.account.domain.User;
import com.alan.microservices.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/{userId}")
    User queryById(@PathVariable("userId") long userId){
        return userService.getById(userId);
    }
}

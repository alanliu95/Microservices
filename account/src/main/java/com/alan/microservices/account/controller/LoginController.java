package com.alan.microservices.account.controller;

import com.alan.microservices.commons.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Result<String> auth(@RequestBody String body) {
        System.out.println(body);
        return Result.success("" + UUID.randomUUID());
    }
}

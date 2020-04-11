package com.alan.microservices.account.controller;

import com.alan.microservices.commons.Result;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {
//    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @PostMapping("/login")
    public Result<String> auth(@RequestBody String body) {
        System.out.println(body);
        return Result.success("" + UUID.randomUUID());
    }
}

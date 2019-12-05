package com.alan.microservices.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String login() {
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean auth() {
        return true;
    }
}

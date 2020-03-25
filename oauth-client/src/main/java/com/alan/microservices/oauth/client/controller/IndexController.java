package com.alan.microservices.oauth.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class IndexController {

    //    //测试接口
    private String res1="default";
    @GetMapping("res1")
    @PreAuthorize("hasAnyAuthority('OP_READ_RES1')")
//    @PreAuthorize("hasRole('USER')")
    public String getRes1() {
        return String.format("res1=%s",res1);
    }


    @PostMapping("res1")
    @PreAuthorize("hasAnyAuthority('OP_UPDATE_RES1')")
    public String updateRes1(String res1,Principal principal) {
        if(!StringUtils.isEmpty(res1))
            this.res1=res1;
        return getRes1()+"--"+principal.toString();
    }

}
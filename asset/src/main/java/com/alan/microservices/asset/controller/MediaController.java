package com.alan.microservices.asset.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MediaController {
    @PostMapping("/media")
    public String upload(){
        System.out.println("收到请求");
        return "ok";

    }
//    @PostMapping("/media")
//    public String upload(@RequestParam("name") String name,
//                         @RequestParam("type") String type,
//                         @RequestParam("file") MultipartFile file){
//        System.out.println(name);
//        System.out.println(type);
//        System.out.println(file);
//        return "ok";
//
//    }
}

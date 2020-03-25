package com.alan.microservices.asset.controller;


import com.alan.microservices.asset.model.MosForm;
import com.alan.microservices.asset.service.MosAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/mos")
public class MosAuthController {
    public static final Logger logger=LoggerFactory.getLogger(MosAuthController.class);
    @Autowired
    private MosAuthService mosAuthService;
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public String auth(MosForm form, HttpServletResponse response){
        logger.debug("url:/auth token:",form.getUsername());
        if(mosAuthService.authenticate(form.getUsername()))
            return "pass";
        else
            response.setStatus(400);
        return "fail";
    }
    @RequestMapping(value = "/acl",method = RequestMethod.POST)
    public String acl(MosForm form, HttpServletResponse response){
        logger.info("url:/acl form:{}",form);
        if(mosAuthService.authorize(form.getUsername(),form.getTopic(),form.getAcc()))
            return "pass";
        else
            response.setStatus(400);
        return "fail";

    }
    @RequestMapping(value = "/superuser",method = RequestMethod.POST)
    public String superuser(MosForm form, HttpServletResponse response){
        logger.info("url:/superuser form:{}",form);
        String token=form.getUsername();
        if(token.indexOf("super")>=0)
            return "pass";
        else
            response.setStatus(400);
        return "deny";
    }
}

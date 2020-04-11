package com.alan.microservices.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({"com.alan.microservices.commons.account.service"})
@EnableDiscoveryClient
public class OauthServerApplication {
    public static void main(String[] args){
        SpringApplication.run(OauthServerApplication.class,args);
    }
}
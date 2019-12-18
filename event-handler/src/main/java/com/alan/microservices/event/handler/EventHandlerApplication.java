package com.alan.microservices.event.handler;

import com.alan.microservices.commons.asset.service.SiteFeignSvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@SpringBootApplication
@EnableFeignClients({"com.alan.microservices.commons.asset.service"})
@EnableDiscoveryClient
//@ComponentScan({"com.alan.microservices.event.handler", "com.alan.microservices.commons.asset.service"})
public class EventHandlerApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(EventHandlerApplication.class, args);
//        DevStatusHandler devStatusHandler=context.getBean(DevStatusHandler.class);
//        devStatusHandler.start();
        SiteFeignSvc siteFeignSvc = context.getBean(SiteFeignSvc.class);
        System.out.println(siteFeignSvc);
        System.out.println("hello");
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

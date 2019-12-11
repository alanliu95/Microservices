package com.alan.microservices.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class BeanConfig {
    //    @Bean("queue")
//    LinkedBlockingQueue<Message> queue(){
//        return new LinkedBlockingQueue<>();
//    }
    @Autowired
    Config config;


}

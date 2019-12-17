package com.alan.microservices.event.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class EventHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventHandlerApplication.class, args);
        System.out.println("hello");
    }
}

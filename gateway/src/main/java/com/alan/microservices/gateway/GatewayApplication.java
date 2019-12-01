package com.alan.microservices.gateway;

//import hello.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {
    public static void main(String[] args) {
//        Hello.hello();
        SpringApplication.run(GatewayApplication.class, args);
    }
}
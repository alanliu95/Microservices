package com.alan.microservices.event.handler.domain;

import lombok.Data;

@Data
public class Device {
    private Integer id;
    private Integer siteId;
    private String name;
    private String token;
    private Integer devType;
    private Float longitude;
    private Float latitude;
    private String info;
}

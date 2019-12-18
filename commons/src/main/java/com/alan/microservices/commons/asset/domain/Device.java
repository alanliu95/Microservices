package com.alan.microservices.commons.asset.domain;

import lombok.Data;

@Data
public class Device {
    private Long id;
    private Long siteId;
    private String name;
    private String token;
    private Integer devType;
    private Float longitude;
    private Float latitude;
    private String info;
}

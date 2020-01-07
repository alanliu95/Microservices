package com.alan.microservices.commons.asset.domain;

import lombok.Data;

@Data
public class DeviceDetail {
    private Long id;
    private Long siteId;
    private String name;
    private String token;
    private Integer devType;
    private String devTypeName;
    private Float longitude;
    private Float latitude;
    private String info;
}


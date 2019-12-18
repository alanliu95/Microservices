package com.alan.microservices.commons.asset.domain;

import lombok.Data;

@Data
public class Site {
    private Long id;
    private String name;
    private Float longitude;
    private Float latitude;
}

package com.alan.microservices.event.handler.etl.status;

import lombok.Data;

@Data
public class DeviceStatus {
    private String token;
    private Integer status;
}

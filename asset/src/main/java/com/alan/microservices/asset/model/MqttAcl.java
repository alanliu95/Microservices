package com.alan.microservices.asset.model;

import lombok.Data;

@Data
public class MqttAcl {
    private long id;
    private String token;
    private String topic;
    private String permission;
}

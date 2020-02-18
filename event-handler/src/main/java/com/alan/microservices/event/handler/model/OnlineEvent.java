package com.alan.microservices.event.handler.model;

import lombok.Data;
@Data
public class OnlineEvent {
    private String token;
    private Integer status;

    public OnlineEvent(String token, Integer status) {
        this.token = token;
        this.status = status;
    }
}

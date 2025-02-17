package com.alan.microservices.event.handler.service.mqtt;

import lombok.Data;

@Data
public class Message {
    private String topic;
    private String payload;

    public Message(String topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }
}

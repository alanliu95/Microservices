package com.alan.microservices.mqtt;

import lombok.Data;

@Data
public class TopicAndMsg {
    private String topic;
    private String msg;
}

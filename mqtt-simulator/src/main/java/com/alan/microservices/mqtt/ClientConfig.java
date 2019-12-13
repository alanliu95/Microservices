package com.alan.microservices.mqtt;

import lombok.Data;

@Data
public class ClientConfig {
    private String brokerUrl;
    private String clientId;
    private String topic;
    private int qos;
    private int interval;
    private TopicAndMsg lwt;
    private TopicAndMsg afterConnect;
    private TopicAndMsg beforeClose;

}

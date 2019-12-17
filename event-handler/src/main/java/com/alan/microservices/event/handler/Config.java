package com.alan.microservices.event.handler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("mqtt")
public class Config {
    private String brokerUrl;
    private String clientId;
    private Boolean cleanSession;
    private Pipeline[] pipelines;

    @Data
    static public class Pipeline {
        private String mqttTopic;
        private String kafkaTopic;
        private int consumerCnt;
    }
}

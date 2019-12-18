package com.alan.microservices.event.handler.etl.status;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("status")
public class DevStatusHandlerConfig {
    private String kafkaTopic;
    private int partitions;
}

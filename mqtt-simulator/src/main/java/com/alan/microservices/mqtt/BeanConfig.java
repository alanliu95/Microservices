package com.alan.microservices.mqtt;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BeanConfig {
    @Bean
    ObjectMapper jacksonOM() {
        return new ObjectMapper();
    }

    @Bean
    ClientConfig[] clientConfigs(ObjectMapper mapper) throws IOException {
        return mapper.readValue(BeanConfig.class.getResource("/mqtt_config.json"), ClientConfig[].class);
    }
}

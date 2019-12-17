package com.alan.microservices.event.handler;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class BeanConfig {
    Config config;
    KafkaProducer kafkaProducer;

    @Autowired
    public void setKafkaProducer(Config config) throws IOException {
        this.config = config;
        Properties properties = new Properties();
        properties.load(BeanConfig.class.getResourceAsStream("/kafkaProducer.properties"));
        kafkaProducer = new KafkaProducer(properties);
    }

    @PostConstruct
    public void construct() throws IOException, MqttException {
        //记录 mqtt kafka topic映射关系
        Map<String, BlockingQueue<String>> map = new HashMap<>();
        for (Config.Pipeline pipeline : config.getPipelines()) {
            BlockingQueue<String> queue = new LinkedBlockingQueue<>();
            map.put(pipeline.getMqttTopic(), queue);
            new Consumer(kafkaProducer, queue, pipeline.getKafkaTopic(), pipeline.getConsumerCnt()).start();
        }
        String[] mqttTopics = map.keySet().toArray(new String[map.size()]);
        new Producer(config.getBrokerUrl(), config.getClientId(), config.getCleanSession(), mqttTopics, map);
    }

}

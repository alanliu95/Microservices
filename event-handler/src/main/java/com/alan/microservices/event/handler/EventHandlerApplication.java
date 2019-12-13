package com.alan.microservices.event.handler;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


@SpringBootApplication
public class EventHandlerApplication {
    public static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws IOException, MqttException {
        ApplicationContext context = SpringApplication.run(EventHandlerApplication.class, args);

        Properties properties = new Properties();
        properties.load(Consumer.class.getResourceAsStream("/producer.properties"));
        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        Config config = context.getBean(Config.class);

        Map<String, BlockingQueue<String>> map = new HashMap<>();
        for (Config.Pipeline pipeline : config.getPipelines()) {
            BlockingQueue<String> queue = new LinkedBlockingQueue<>();
            map.put(pipeline.getMqttTopic(), queue);
            new Consumer(kafkaProducer, queue, pipeline.getKafkaTopic(), pipeline.getConsumerCnt()).start();
        }
        String[] mqttTopics = map.keySet().toArray(new String[map.size()]);
        new Producer(config.getBrokerUrl(), config.getClientId(), mqttTopics, map);
//        System.out.println("ddd");
        while (true) ;
    }
}

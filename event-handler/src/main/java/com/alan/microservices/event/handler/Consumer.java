package com.alan.microservices.event.handler;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private KafkaProducer kafkaProducer;
    private String topic;
    private int threadNum;
    private AtomicInteger key;

    //    @Autowired
    public Consumer(KafkaProducer kafkaProducer, BlockingQueue<String> queue, String topic, int threadNum) throws IOException {
        this.kafkaProducer = kafkaProducer;
        this.queue = queue;
        this.threadNum = threadNum;
        this.topic = topic;
        key = new AtomicInteger(0);
//        topicMap =new HashMap<>();
//        for (Config.Pipeline pipeline:config.getPipelines()){
//            topicMap.put(pipeline.getMqttTopic(),pipeline.getKafkaTopic());
//        }
//        Properties properties=new Properties();
//        properties.load(Consumer.class.getResourceAsStream("/producer.properties"));
//        kafkaProducer=new KafkaProducer<>(properties);
    }

    @Override
    public void run() {
        String message;
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                message = queue.take();
                kafkaProducer.send(new ProducerRecord<String, String>(topic, Integer.toString(key.getAndIncrement()), message));
                if (i == Integer.MAX_VALUE)
                    i = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        kafkaProducer.close();
    }

    //    @PostConstruct
    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {
            executorService.submit(this);
        }
    }
}

package com.alan.microservices.event.handler;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Set;

public class KafkaUtil {
    public static <k, v> void resetOffset(KafkaConsumer<k, v> consumer) {
        Set<TopicPartition> partitions = consumer.assignment();
        if (partitions == null) {
            System.out.println("无已订阅主题分区");
            return;
        }

        consumer.seekToBeginning(partitions);
        partitions.stream().forEach(tp -> {
            System.out.println(String.format("topic:%s,partition:%s,offset:%s",
                    tp.topic(), tp.partition(), consumer.position(tp)));
        });
//        partitions.stream().forEach(tp -> System.out.println(tp.topic()+","+tp.partition()));
    }
}

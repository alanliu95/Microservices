package com.alan.microservices.event.handler;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.junit.experimental.theories.internal.Assignments;

import java.time.Duration;
import java.util.*;

public class ConsumerDemo {
    /**
     * 按照分区维度消费数据，batch方式，同步提交消费位移
     */
    KafkaConsumer consumer;

    void ConsumeByPartition() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<String, String>> partitionRecords = records.records(tp);
            for (ConsumerRecord record : partitionRecords) {
                // 业务逻辑处理
                System.out.println(record.partition() + "," + record.value());
            }
            long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
            consumer.commitSync(Collections.singletonMap(tp, new OffsetAndMetadata(lastConsumedOffset + 1)));
        }
    }

    List<String> topics = Arrays.asList("topic1", "topic2");

    void ConsumeByTopic() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (String topic : topics) {
            for (ConsumerRecord<String, String> record : records.records(topic)) {
                System.out.println("消费数据");
            }
        }
    }

    public void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> consumer.wakeup()));
    }

    void ConsumeByPartitions() {
        try {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (TopicPartition tp : records.partitions()) {  //使用partitions方法获取所包含的分区
                for (ConsumerRecord record : records.records(tp)) { //根据分区划分数据
                    // 业务逻辑处理
                    System.out.println(record.partition() + "," + record.value());
                }
            }
        } catch (WakeupException e) {
            //忽略
        } catch (Exception e) {

        } finally {
            consumer.close();
        }

    }

    void consumeBySeek() {
        Set<TopicPartition> assignment;
        do {
            consumer.poll(Duration.ofMillis(100));
            assignment = consumer.assignment();
        } while (assignment.size() == 0);  //保证分区完成
        for (TopicPartition tp : assignment) {
            consumer.seek(tp, 10);
        }
        while (true) {
            consumer.poll(Duration.ofMillis(1000));
            //消费消息
        }
    }

    void consumeByTs() {
        Set<TopicPartition> assignment;
        do {
            consumer.poll(Duration.ofMillis(100));
            assignment = consumer.assignment();
        } while (assignment.size() == 0);  //保证分区完成
        Map<TopicPartition, Long> timeStampToSearch = new HashMap<>();
        for (TopicPartition tp : assignment) {
            timeStampToSearch.put(tp, System.currentTimeMillis() - 1 * 24 * 60 + 1000);
        }
        Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timeStampToSearch);
        for (TopicPartition tp : assignment) {
            OffsetAndTimestamp offsetAndTimestamp = offsets.get(tp);
            if (offsetAndTimestamp != null) {
                consumer.seek(tp, offsetAndTimestamp.offset());
            }
        }
        while (true) {
            consumer.poll(Duration.ofMillis(1000));
            //消费消息
        }
    }
}

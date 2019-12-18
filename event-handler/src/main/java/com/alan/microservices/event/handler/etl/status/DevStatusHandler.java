package com.alan.microservices.event.handler.etl.status;

import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.service.DevFeignSvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Service
@Data
public class DevStatusHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(DevStatusHandler.class);
    private final int THREAD_NUM;
    private final String TOPIC;

    private ObjectMapper objectMapper;
    private DevFeignSvc deviceService;
    private ExecutorService executorService;
    private ConcurrentHashMap<Long, ConcurrentHashMap<Long, Boolean>> statusTable;

    @Autowired
    public DevStatusHandler(DevStatusHandlerConfig config, DevFeignSvc deviceService, ObjectMapper objectMapper) {
        this.THREAD_NUM = config.getPartitions();
        this.TOPIC = config.getKafkaTopic();

        this.deviceService = deviceService;
        this.objectMapper = objectMapper;

        statusTable = new ConcurrentHashMap<>();
        statusTable.put(1L, new ConcurrentHashMap<>());

//        executorService= Executors.newFixedThreadPool(THREAD_NUM);
//        for (int i = 0; i < THREAD_NUM; i++) {
//            Thread thread=new Thread(new HandlerThread(TOPIC),"HandlerThread"+i);
//            thread.start();
//        }
    }

    @PostConstruct
    public void start() throws IOException {
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(new HandlerThread(TOPIC), "HandlerThread-" + i);
            thread.start();
        }
    }

    private class HandlerThread implements Runnable {
        private KafkaConsumer<String, String> consumer;

        public HandlerThread(String kafkaTopic) throws IOException {
            Properties properties = new Properties();
            properties.load(DevStatusHandler.class.getResourceAsStream("/kafkaConsumer.properties"));
            consumer = new KafkaConsumer(properties);
            consumer.subscribe(Arrays.asList(kafkaTopic));
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    if (!records.isEmpty()) {
                        for (ConsumerRecord<String, String> record : records) {
                            LOGGER.debug("线程:{} kafka消息：key={},value={}", Thread.currentThread().getName(), record.key(), record.value());
                            DeviceStatus status = objectMapper.readValue(record.value(), DeviceStatus.class);
                            Device device = deviceService.getByToken(status.getToken());
                            if (device == null) {
//                                LOGGER.debug();
                                throw new RuntimeException("设备不存在");
                            }
                            Map<Long, Boolean> deviceStatus = statusTable.get(1L);
                            deviceStatus.put(device.getId(), status.getStatus() == 0 ? false : true);

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }

        }
    }
}

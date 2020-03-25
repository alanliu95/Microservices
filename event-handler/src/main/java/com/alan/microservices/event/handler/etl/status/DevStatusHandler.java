package com.alan.microservices.event.handler.etl.status;

import com.alan.microservices.commons.asset.service.DevFeignSvc;
import com.alan.microservices.commons.asset.service.SiteFeignSvc;
import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.domain.Site;
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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@Service
@Data
public class DevStatusHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(DevStatusHandler.class);
    private int threadNum;
    private String topic;

    private ObjectMapper objectMapper;
    private DevFeignSvc devFeignSvc;
    private SiteFeignSvc siteFeignSvc;
    private ExecutorService executorService;
    private ConcurrentHashMap<Long, ConcurrentHashMap<Long, Boolean>> statusTable;

    private List<Device> devices;
    private List<Site> sites;
    @Autowired
    public DevStatusHandler(DevStatusHandlerConfig config, DevFeignSvc devFeignSvc, SiteFeignSvc siteFeignSvc, ObjectMapper objectMapper) {
        this.threadNum = config.getPartitions();
        this.topic = config.getKafkaTopic();

        this.siteFeignSvc = siteFeignSvc;
        this.devFeignSvc = devFeignSvc;
        this.objectMapper = objectMapper;


        statusTable = new ConcurrentHashMap<>();

//        this.devices = devFeignSvc.getAllDevices().getData();
//        this.sites = siteFeignSvc.getAll().getData();
//        for (Site site : sites) {
//            ConcurrentHashMap map = new ConcurrentHashMap<Long, Boolean>();
//            for (Device device : devices) {
//                map.put(device.getId(), false);
//            }
//            statusTable.put(site.getId(), map);
//        }

//        executorService= Executors.newFixedThreadPool(THREAD_NUM);
//        for (int i = 0; i < THREAD_NUM; i++) {
//            Thread thread=new Thread(new HandlerThread(TOPIC),"HandlerThread"+i);
//            thread.start();
//        }
    }

    @PostConstruct
    public void start() throws IOException {
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new HandlerThread(topic), "HandlerThread-" + i);
            thread.start();
        }
    }

    private class HandlerThread implements Runnable {
        private int POLL_PERIOD = 100;
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
                Thread.sleep(3000); // todo 等待其他依赖被初始化
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(POLL_PERIOD));
                    if (!records.isEmpty()) {
                        for (ConsumerRecord<String, String> record : records) {
                            LOGGER.debug("线程:{} kafka消息：key={},value={}", Thread.currentThread().getName(), record.key(), record.value());
                            DeviceStatus status = objectMapper.readValue(record.value(), DeviceStatus.class);
                            Device device = devFeignSvc.getByToken(status.getToken()).getData();
                            if (device == null) {
                                LOGGER.error("device token {}", status.getToken());
                                throw new RuntimeException("设备不存在");
                            }
                            ConcurrentHashMap<Long, Boolean> targetSiteDevices = statusTable.get(device.getSiteId());
                            if (targetSiteDevices == null) {
                                targetSiteDevices = new ConcurrentHashMap<Long, Boolean>();
                                statusTable.put(device.getSiteId(), targetSiteDevices);
                            }
                            targetSiteDevices.put(device.getId(), status.getStatus() == 0 ? false : true);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }

        }
    }
}

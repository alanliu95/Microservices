package com.alan.microservices.event.handler.service.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

//@Component
public class Producer implements MqttCallback {
    public static final Logger logger = LoggerFactory.getLogger(Producer.class);
    public static final int QOS = 1;
    private MqttClient mqttClient;
    private Map<String, BlockingQueue<String>> map;

    //    @Autowired
    public Producer(String serverURI, String clientId, boolean cleanSession, String[] topics, Map<String, BlockingQueue<String>> map) throws MqttException {
        this.map = map;
        String tmpDir = System.getProperty("java.io.tmpdir");
        logger.debug("mqtt client 数据持久化文件夹：{}", tmpDir);
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        mqttClient = new MqttClient(serverURI, clientId, dataStore);
        mqttClient.setCallback(this);
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(cleanSession);
        logger.debug("mqtt客户端 连接选项：cleanSession={}", cleanSession);
        mqttClient.connect(connectOptions);
        mqttClient.subscribe(topics);
    }

    @Override
    public void connectionLost(Throwable cause) {
        cause.printStackTrace();
//        logger.error("connectionLost");
    }

    /**
     * 被 "mqtt call" 线程异步调用
     *
     * @param topic
     * @param message
     * @throws MqttException
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        String payload = new String(message.getPayload());
        logger.debug("接收到mqtt消息: {topic:{}, payload:{}}", topic, payload);
        for (String key : map.keySet()) {
            String keyPrefix = key.indexOf('#') >= 0 ? key.substring(0, key.indexOf('#')) : key;
            if (topic.indexOf(keyPrefix) >= 0) {
                logger.debug("转发：mqtt topic:{} -> 阻塞队列:{}", topic, key);
                map.get(key).add(payload);
                return;
            }
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

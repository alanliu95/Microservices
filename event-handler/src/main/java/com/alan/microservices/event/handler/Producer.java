package com.alan.microservices.event.handler;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

//@Component
public class Producer implements MqttCallback {
    public static final Logger logger = LoggerFactory.getLogger(Producer.class);
    public static final int QOS = 1;
    private MqttClient mqttClient;
    private Map<String, BlockingQueue<String>> map;

    //    @Autowired
    public Producer(String serverURI, String clientId, String[] topics, Map<String, BlockingQueue<String>> map) throws MqttException {
        this.map = map;
        String tmpDir = System.getProperty("java.io.tmpdir");
        logger.debug("mqtt client 数据持久化文件夹：{}", tmpDir);
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        mqttClient = new MqttClient(serverURI, clientId, dataStore);
//            MqttConnectOptions connectOptions=new MqttConnectOptions();

        mqttClient.setCallback(this);
        mqttClient.connect();
        mqttClient.subscribe(topics);
    }

    @Override
    public void connectionLost(Throwable cause) {
        cause.printStackTrace();
//        logger.error("connectionLost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        String payload = new String(message.getPayload());
        logger.debug("received msg: {topic:{}, payload:{}}", topic, payload);
        for (String key : map.keySet()) {
            String keyPrefix = key.indexOf('#') >= 0 ? key.substring(0, key.indexOf('#')) : key;
            if (topic.indexOf(keyPrefix) >= 0) {
                logger.debug("mqtt topic:{} matches map.key:{}", topic, key);
                map.get(key).add(payload);
                return;
            }
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

package com.alan.microservices.event.handler;

import org.eclipse.paho.client.mqttv3.*;
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
    public Producer(String serverURI, String clientId, String[] topics, Map<String, BlockingQueue<String>> map) throws MqttException {
        this.map = map;
        mqttClient = new MqttClient(serverURI, clientId);
//            MqttConnectOptions connectOptions=new MqttConnectOptions();
        mqttClient.setCallback(this);
        mqttClient.connect();

        mqttClient.subscribe(topics);

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        logger.info("received msg: {topic:{}, payload:{}}", topic, payload);
//        queue.offer(new Message(topic,payload));
        map.get(topic).add(payload);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

package com.alan.microservices.event.handler.test;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttWildcardTest implements MqttCallback {
    public static final Logger logger = LoggerFactory.getLogger(MqttWildcardTest.class);

    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://192.168.1.13:1883", "Wildcard");
        client.connect();
        client.setCallback(new MqttWildcardTest());
        client.subscribe("/site/status/#");
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        logger.info("received msg: {topic:{}, payload:{}}", topic, payload);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

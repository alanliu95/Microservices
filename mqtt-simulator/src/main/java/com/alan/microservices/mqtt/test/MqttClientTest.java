package com.alan.microservices.mqtt.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientTest {
    public static final MemoryPersistence dataStore = new MemoryPersistence();
    public static final String serverUrl = "tcp://192.168.1.13:1883";
    public static final String clientId = "test";

    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient(serverUrl, clientId, dataStore);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                cause.printStackTrace();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws MqttException {
                System.out.println("messageArrived");
                System.out.println(1 / 0);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        client.connect();
        client.subscribe("/hello", 1);
        client.publish("/hello", new MqttMessage("hello".getBytes()));
    }
}

package com.alan.microservices.mqtt.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttAsyncClientTest {
    public static final MemoryPersistence dataStore = new MemoryPersistence();
    public static final String serverUrl = "tcp://192.168.1.13:1883";
    public static final String clientId = "test";

    public static void main(String[] args) throws MqttException {
        IMqttToken token;
        MqttAsyncClient asyncClient = new MqttAsyncClient(serverUrl, clientId, dataStore);
        asyncClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
//                cause.printStackTrace();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws MqttException {
                System.out.printf("thread %d :", Thread.currentThread().getId());
//                System.out.println(1/0);
                System.out.println("topic:" + topic);
                System.out.println("Qos:" + message.getQos());
                System.out.println("message content:" + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.printf("thread %d :", Thread.currentThread().getId());
                System.out.println("消息发送成功");
//                System.out.println(1/0);
            }
        });
        token = asyncClient.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
//                System.out.println(asyncActionToken);
                System.out.printf("thread %d :", Thread.currentThread().getId());
                System.out.println("connection succeed");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                System.out.println("connection failed");
            }
        });
        token.waitForCompletion();
        token = asyncClient.subscribe("/hello", 1);//.waitForCompletion();
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                System.out.println("订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                System.out.println("订阅失败");
            }
        });
        token.waitForCompletion();
        token = asyncClient.publish("/hello", new MqttMessage("hello".getBytes()));
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                System.out.printf("thread %d :发布成功\n", Thread.currentThread().getId());
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                System.out.println("发布失败");
            }
        });
        token.waitForCompletion();
//        token=asyncClient.disconnect();
//        token.waitForCompletion();
        //asyncClient.close();
//        System.out.println("release all resources");
    }
}

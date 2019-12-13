package com.alan.microservices.mqtt.sample;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttAsynPublishSample {

    public static void main(String[] args) {

        String topic = "MQTT Async Publish Examples";
        String content = "Message from MqttAsyncPublishSample";
        int qos = 1;
        String broker = "tcp://192.168.1.13:1883";
        String clientId = "MqttAsyncSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttAsyncClient asyncClient = new MqttAsyncClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: " + broker);
            IMqttToken conToken = asyncClient.connect(connOpts);
            conToken.waitForCompletion();
            System.out.println("Connected");

            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            asyncClient.publish(topic, message, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println("Message published");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    System.out.println("Message failed to publish");
                }
            });

            asyncClient.disconnect().waitForCompletion();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}

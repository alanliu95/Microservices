package com.alan.microservices.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MqttSimulator implements Runnable, MqttCallback {
    private ClientConfig config;
    MessGenerator generator;

    public MqttSimulator(ClientConfig config, MessGenerator generator) {
        this.config = config;
        this.generator = generator;
    }

    @Override
    public void run() {
        MqttClient mqttClient = null;
        MqttMessage mqttMessage;
        TopicAndMsg notification = null;
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
            mqttClient = new MqttClient(config.getBrokerUrl(), config.getClientId(), dataStore);
            notification = config.getLwt();
            if (notification != null) {
                connectOptions.setWill(notification.getTopic(), notification.getMsg().getBytes(), 1, true);
            }
            mqttClient.connect(connectOptions);
            mqttClient.setCallback(this);
            notification = config.getAfterConnect();
            if (notification != null) {
                mqttMessage = new MqttMessage(notification.getMsg().getBytes());
                mqttMessage.setRetained(true);
                mqttClient.publish(notification.getTopic(), mqttMessage);
            }
            while (!Thread.currentThread().isInterrupted()) {
                mqttMessage = new MqttMessage(generator.oneMessage());
                mqttMessage.setQos(config.getQos());
                mqttClient.publish(config.getTopic(), mqttMessage);
                Thread.currentThread().sleep(config.getInterval());
            }

        } catch (MqttException e) {
            e.printStackTrace();
            return;
        } catch (InterruptedException e) {

        } finally {
            try {
                notification = config.getBeforeClose();
                if (notification != null) {
                    mqttMessage = new MqttMessage(notification.getMsg().getBytes());
                    mqttMessage.setRetained(true);
                    mqttClient.publish(notification.getTopic(), mqttMessage);
                }

                mqttClient.disconnect();
            } catch (MqttException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection to brokerUrl" + " lost!" + cause);
        System.exit(-1);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

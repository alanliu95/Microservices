package com.alan.microservices.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements MqttCallback, Runnable {
    public static Logger logger = LoggerFactory.getLogger(Client.class);
    private MqttClient mqttClient;
    private MqttConnectOptions conOpt;
    private MessGenerator generator;
    private String topicName;
    private int qos;
    private int interval;

//        String tmpDir = System.getProperty("java.io.tmpdir");
//        System.out.println(tmpDir);
//        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
//            conOpt = new MqttConnectOptions();
//            conOpt.setCleanSession(cleanSession);
//            if (password != null) {
//                conOpt.setPassword(password.toCharArray());
//            }
//            if (userName != null) {
//                conOpt.setUserName(userName);
//            }
// client = new MqttClient(brokerUrl, clientId, dataStore);
//client.setCallback(this);

    // MqttSecurityException
    public void publish(String topicName, int qos, byte[] payload) throws MqttException {

//        String time = new Timestamp(System.currentTimeMillis()).toString();
//        log("Publishing at: " + time + " to topic \"" + topicName + "\" qos " + qos);

        // Create and configure a message
        MqttMessage message = new MqttMessage(payload);
        message.setQos(qos);

        // Send the message to the server, control is not returned until
        // it has been delivered to the server meeting the specified
        // quality of service.
        mqttClient.publish(topicName, message);
    }


    public Client(MqttClient mqttClient, MqttConnectOptions conOpt, MessGenerator generator, String topicName, int qos) {
        this.mqttClient = mqttClient;
        this.conOpt = conOpt;
        this.generator = generator;
        this.topicName = topicName;
        this.qos = qos;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        MqttMessage message;
        try {
            mqttClient.connect(conOpt);
            mqttClient.setCallback(this);
            while (!Thread.currentThread().isInterrupted()) {
                message = new MqttMessage(generator.oneMessage());
                message.setQos(qos);

                // Send the message to the server, control is not returned until
                // it has been delivered to the server meeting the specified
                // quality of service.
                // 同步方法？？？
                mqttClient.publish(topicName, message);
                Thread.currentThread().sleep(interval);
            }

        } catch (InterruptedException e) {

        } catch (MqttException mqttException) {
            mqttException.printStackTrace();
        } finally {
            try {
                mqttClient.disconnect();
            } catch (Exception e) {
            }
        }
    }

    /****************************************************************/
    /* Methods to implement the MqttCallback interface */
    /****************************************************************/

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        // Called when the connection to the server has been lost.
        // An application may choose to implement reconnection
        // logic at this point. This sample simply exits.
        System.out.println("Connection to brokerUrl" + " lost!" + cause);
        System.exit(-1);
    }

    /**
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
        //System.out.println("delivery Completed:");
//        try {
//            MqttMessage mess=token.getMessage();
//
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * @throws InterruptedException
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws MqttException, InterruptedException {
        // Called when a message arrives from the server that matches any
        // subscription made by the client
        // Thread current = Thread.currentThread();
        // System.out.println(current.getPriority());
        // System.out.println(current.getName());
        // System.out.println(current.activeCount());
        // System.out.println(current.getId());
        // System.out.println(current.getThreadGroup());
        // System.out.println(current.getStackTrace());
        // System.out.println(current.hashCode());
        // System.out.println(current.toString());

//        String time = new Timestamp(System.currentTimeMillis()).toString();
//        System.out.println("Time:\t" + time + "  Topic:\t" + topic + "  Message:\t" + new String(message.getPayload())
//                + "  QoS:\t" + message.getQos());
//        if (topic.equals("/paper")) {
//            ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//            byte[] json = new String(message.getPayload()).getBytes();
//            try {
//                // 尝试从JSON中读取对象
//                PaperInfo info = mapper.readValue(json, PaperInfo.class);
//                System.out.println(info);
//                Wanfang wf = new Wanfang(info, "E:\\Liusong\\paper\\");
//                if (wf.download()) {
//                    System.out.println("download finished");
//                    publish("/result", 1, "succeed".getBytes());
//                } else
//                    System.out.println("download failed");
//            } catch (IOException e) {
//                // e.printStackTrace();
//                System.out.println("json format error");
//                return;
//            }
//        }
    }


    //    public void subscribe(String topicName, int qos) throws MqttException {
//        log("subscribing at: " + " to topic \"" + topicName + "\" qos " + qos);
//        // Create and configure a message
//        // Send the message to the server, control is not returned until
//        // it has been delivered to the server meeting the specified
//        // quality of service.
//        client.subscribe(topicName, qos);
//    }
//
//    public void unsubscribe(String topicName) throws MqttException {
//        log("unsubscribing at: " + " to topic \"" + topicName);
//        client.unsubscribe(topicName);
//    }
}

package com.alan.microservices.mqtt;

import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttSimulatorApplicationTests {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    ClientConfig[] configs;

    //@Test
    public void lastWillTestament() throws MqttException, InterruptedException {
        if (ArrayUtils.isEmpty(configs)) throw new NullPointerException();
        ClientConfig config = configs[0];
        System.out.println(config);
        MqttClient mqttClient = new MqttClient(config.getBrokerUrl(), config.getClientId());
        Client client = new Client(mqttClient, null, new SimStatus(config.getClientId(), mapper), config.getTopic(), config.getQos());
        client.setInterval(config.getInterval());
        Thread thread = new Thread(client);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void simulatorTest() throws InterruptedException {
        if (ArrayUtils.isEmpty(configs)) throw new NullPointerException();
        ClientConfig config = configs[0];
        System.out.println(config);
        MqttSimulator mqttSimulator = new MqttSimulator(config, new SimStatus(config.getClientId(), mapper));
        Thread thread = new Thread(mqttSimulator);
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
        thread.join();
    }


}

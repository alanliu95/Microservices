package com.alan.microservices.mqtt;

import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MqttSimulatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttSimulatorApplication.class, args);
    }

    @Autowired
    ClientConfig[] configs;
    @Autowired
    ObjectMapper mapper;

    @PostConstruct
    public void start() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (ClientConfig config : configs) {

            MqttSimulator mqttSimulator = new MqttSimulator(config, new SimStatus(config.getClientId(), mapper));
            executor.submit(mqttSimulator);
        }
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(MqttClientApplication.class, args);
//        //MqttConnectOptions opt=new MqttConnectOptions();
//        //opt.setCleanSession(true);
//        ObjectMapper mapper=new ObjectMapper();
//        ClientConfig[] configs=mapper.readValue(MqttClientApplication.class.getResource("/mqtt_config.json"),ClientConfig[].class);
//        int count=configs.length;
//        //Client[] clients=new Client[count];
//        ExecutorService executor= Executors.newFixedThreadPool(count);
//        for(ClientConfig config :configs){
//            System.out.println(config);
//            //需要保证每个客户端 clientID唯一
//            MqttClient mqttClient=new MqttClient(config.getBrokerUrl(),config.getClientId());
//            Client client=new Client(mqttClient,null,new SimStatus(config.getClientId(),mapper),config.getTopic(),config.getQos());
//            client.setInterval(config.getInterval());
//            executor.submit(client);
//        }
//        //System.in.read();
//        //Thread.currentThread().sleep(15000);
//        //executor.shutdown();
//    }

}

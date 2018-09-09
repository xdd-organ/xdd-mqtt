package com.java.xdd.mqtt;

import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

import java.util.Random;

public class P2PMqttTest {



    public MqttPahoClientFactory clientFactory() {
        DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
        clientFactory.setServerURIs("tcp://localhost:1883");
        clientFactory.setUserName("admin");
        clientFactory.setPassword("admin");
        return clientFactory;
    }

    public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("aaaa", clientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(1);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        return messageHandler;
    }

    public static void main(String[] args) {
        P2PMqttTest p2PMqttTest = new P2PMqttTest();
        MqttPahoClientFactory mqttPahoClientFactory = p2PMqttTest.clientFactory();
        MqttPahoMessageHandler mqttPahoMessageHandler = p2PMqttTest.mqttOutbound(mqttPahoClientFactory);


    }

}
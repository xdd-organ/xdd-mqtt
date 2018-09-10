package com.java.xdd.mqtt.mqtt.demo;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * 消息发送接口，不需要实现，spring会通过代理的方式实现
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {

	void sendMessage(Message<?> message);
}
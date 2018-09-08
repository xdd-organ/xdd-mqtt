package com.java.xdd.mqtt.configuration.mqtt;

import java.util.Random;

import com.java.xdd.mqtt.demo.ReceiveMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfiguration {

	private Random rand = new Random();

	private String clientId = "aaa";

	// ==================== 连接activemq ====================
	@Bean
	public MqttPahoClientFactory clientFactory() {
		DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
		clientFactory.setServerURIs("tcp://localhost:1883");
		clientFactory.setUserName("admin");
		clientFactory.setPassword("admin");
		return clientFactory;
	}

	// ==================== 生产者 ====================
	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel") // 绑定生产者
	public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
				clientId + String.format("%05d", rand.nextInt(10000)), clientFactory);
		messageHandler.setAsync(true);
		messageHandler.setDefaultQos(1);
		messageHandler.setDefaultRetained(false);
		messageHandler.setAsyncEvents(false);
		return messageHandler;
	}

	// ==================== 消费者 ====================
    @Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel") // 绑定消费者
	public MessageHandler handler() {
		return new ReceiveMessageHandler();
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				clientId + String.format("%05d", rand.nextInt(10000)), clientFactory(), "mqtt-topic-demo");
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}


}

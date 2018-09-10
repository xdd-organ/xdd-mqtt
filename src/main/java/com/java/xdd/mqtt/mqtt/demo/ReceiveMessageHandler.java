package com.java.xdd.mqtt.mqtt.demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class ReceiveMessageHandler implements MessageHandler {

	protected static Logger logger = (Logger) LoggerFactory.getLogger(ReceiveMessageHandler.class);

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		String str = (String) message.getPayload();
		System.out.println("接收到消息：" + str);
	}
}
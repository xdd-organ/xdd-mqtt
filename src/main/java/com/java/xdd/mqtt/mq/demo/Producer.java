package com.java.xdd.mqtt.mq.demo;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("producer")
public class Producer {
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
	private JmsMessagingTemplate jmsTemplate;
	@Autowired
	private JmsTemplate jmsTemplate2;

	// 发送消息，destination是发送到的队列，message是待发送的消息
	public void sendMessage(Destination destination, final String message) {
		jmsTemplate.convertAndSend(destination, message);
//		jmsTemplate2.convertAndSend(destination, message);
	}

	public void sendMessage2(Destination destination, final String message2) {
		jmsTemplate2.send(destination, new MessageCreator() {
			@Override
			public javax.jms.Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(message2);
				message.setStringProperty("systemcode", "aaaa");//指定消费者的 selector = "systemcode=aaaa"
				return message;
			}
		});
//		jmsTemplate2.convertAndSend(destination, message);
	}
	public void sendMessage3(Destination destination, final String message2) {
		jmsTemplate.send(destination, new Message() {
			@Override
			public Object getPayload() {
				return message2;
			}

			@Override
			public MessageHeaders getHeaders() {
				HashMap<String, Object> m = new HashMap<>();
				m.put("systemcode", "aaaa");
				MessageHeaders messageHeaders = new MessageHeaders(m);
				return messageHeaders;
			}
		});
//		jmsTemplate2.convertAndSend(destination, message);
	}
}

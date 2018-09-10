package com.java.xdd.mqtt.mq.demo;

import javax.jms.Destination;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
 
@Service("producer")
public class Producer {
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
	private JmsMessagingTemplate jmsTemplate;
//	@Autowired
//	private JmsTemplate jmsTemplate2;

	// 发送消息，destination是发送到的队列，message是待发送的消息
	public void sendMessage(Destination destination, final String message) {
		jmsTemplate.convertAndSend(destination, message);
//		jmsTemplate2.convertAndSend(destination, message);
	}
}

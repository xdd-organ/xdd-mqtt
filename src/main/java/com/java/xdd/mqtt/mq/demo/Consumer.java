package com.java.xdd.mqtt.mq.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:"+text);
	}

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.queue", selector = "systemcode='aaaa'")//接收指定的消费
	public void receiveQueue2(String text) {
		System.out.println("Consumer2收到的报文为:"+text);
	}


}
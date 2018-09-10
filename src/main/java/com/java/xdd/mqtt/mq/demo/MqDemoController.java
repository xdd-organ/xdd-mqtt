package com.java.xdd.mqtt.mq.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class MqDemoController {

    @Autowired
    private Producer producer;

    @RequestMapping("demo")
    public String demo() {
        producer.sendMessage(new ActiveMQQueue("mytest.queue"), "send message");
        producer.sendMessage2(new ActiveMQQueue("mytest.queue"), "send message2");
        producer.sendMessage3(new ActiveMQQueue("mytest.queue"), "send message3");
        return "succ";
    }


}

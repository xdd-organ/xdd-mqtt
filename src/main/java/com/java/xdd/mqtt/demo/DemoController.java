package com.java.xdd.mqtt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired(required = false)
    private MqttMessageGateway gateway;

    @RequestMapping("demo")
    public String demo() {

        Message<String> message = MessageBuilder.withPayload("修改笔记，新的笔记名称：")
                .setHeader(MqttHeaders.TOPIC, "mqtt-topic-demo").build();
        gateway.sendMessage(message);

        return "succ";
    }


}

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

//        Message<String> message = MessageBuilder.withPayload("cba@项客户端id为cba推送消息")//向客户端id为cba发送消息
//                .setHeader(MqttHeaders.TOPIC, "channel@ptp").build();
        Message<String> message = MessageBuilder.withPayload("向客户端推送消息")
                .setHeader(MqttHeaders.TOPIC, "channel@ptp").build();
        gateway.sendMessage(message);

        return "succ";
    }


}

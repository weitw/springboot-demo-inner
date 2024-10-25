package com.weitw.study.sbt.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        rocketMQTemplate.convertAndSend("springboot-topic", message);
        return "Message sent: " + message;
    }
}

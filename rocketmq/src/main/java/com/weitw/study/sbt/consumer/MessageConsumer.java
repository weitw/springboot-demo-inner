package com.weitw.study.sbt.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "springboot-topic", consumerGroup = "springboot-consumer-group")
public class MessageConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }
}

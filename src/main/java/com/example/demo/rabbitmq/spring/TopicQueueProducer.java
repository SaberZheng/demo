package com.example.demo.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbitmq.MqConstants.*;
import static com.example.demo.rabbitmq.MqConstants.TOPIC_KEY4;

/**
 * @author Amy
 * @date 2020/8/1 15:27
 */
@Component
public class TopicQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TopicQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msg1 = "Hello topic key 1";
        System.out.println("send msg :" + msg1);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, TOPIC_KEY3, msg1);
        String msg2 = "Hello topic key 2";
        System.out.println("send msg :" + msg2);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, TOPIC_KEY4, msg2);
    }
}

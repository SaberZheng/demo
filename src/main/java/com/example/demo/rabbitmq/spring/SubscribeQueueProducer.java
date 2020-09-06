package com.example.demo.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbitmq.MqConstants.SUBSCRIBE_EXCHANGE;

/**
 * @author Amy
 * @date 2020/7/28 14:00
 */
@Component
public class SubscribeQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SubscribeQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msg = "Hello World";
        System.out.println("send msg :" + msg);
        rabbitTemplate.convertAndSend(SUBSCRIBE_EXCHANGE, "", msg);
    }
}

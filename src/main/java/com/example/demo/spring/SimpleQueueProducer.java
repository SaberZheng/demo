package com.example.demo.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.SIMPLE_QUEUE;

/**
 * @author Amy
 * @date 2020/7/26 23:12
 */
@Component
public class SimpleQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SimpleQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msg = "Hello World";
        System.out.println("send msg :" + msg);
        rabbitTemplate.convertAndSend(SIMPLE_QUEUE, msg);
    }
}

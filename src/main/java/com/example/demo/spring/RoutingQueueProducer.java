package com.example.demo.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.*;

/**
 * @author Amy
 * @date 2020/8/1 15:15
 */
@Component
public class RoutingQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RoutingQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msg1 = "Hello routing key 1";
        System.out.println("send msg :" + msg1);
        rabbitTemplate.convertAndSend(ROUTING_EXCHANGE, ROUTING_KEY1, msg1);
        String msg2 = "Hello routing key 2";
        System.out.println("send msg :" + msg2);
        rabbitTemplate.convertAndSend(ROUTING_EXCHANGE, ROUTING_KEY2, msg2);
    }
}

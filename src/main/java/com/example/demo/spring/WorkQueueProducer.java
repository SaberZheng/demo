package com.example.demo.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.WORK_QUEUE;

/**
 * @author Amy
 * @date 2020/7/28 13:41
 */
@Component
public class WorkQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public WorkQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        for (int i = 0; i < 10; i++) {
            String s = String.format("%d hello world", i);
            System.out.println("send msg：" + s);
            rabbitTemplate.convertAndSend(WORK_QUEUE, s);
        }
    }
}

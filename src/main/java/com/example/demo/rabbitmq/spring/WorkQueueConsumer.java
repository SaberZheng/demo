package com.example.demo.rabbitmq.spring;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbitmq.MqConstants.WORK_QUEUE;

/**
 * @author Amy
 * @date 2020/7/28 13:47
 */
@Component
public class WorkQueueConsumer {

    @RabbitListener(queuesToDeclare = @Queue(WORK_QUEUE))
    public void receive1(String msg) {
        System.out.println("[1]---receive msg：" + msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(WORK_QUEUE))
    public void receive2(String msg) {
        System.out.println("[2]---receive msg：" + msg);
    }
}

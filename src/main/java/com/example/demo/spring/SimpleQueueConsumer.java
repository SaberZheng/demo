package com.example.demo.spring;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.SIMPLE_QUEUE;

/**
 * @author Amy
 * @date 2020/7/26 23:19
 * RabbitListener 默认是持久化非独占不自动删除的队列
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(name = SIMPLE_QUEUE, durable = "true"))
public class SimpleQueueConsumer {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("receive msg：" + msg);
    }
}

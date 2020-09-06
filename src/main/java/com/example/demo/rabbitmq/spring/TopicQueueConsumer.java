package com.example.demo.rabbitmq.spring;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbitmq.MqConstants.*;

/**
 * @author Amy
 * @date 2020/8/1 15:26
 */
@Component
public class TopicQueueConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = TOPIC_EXCHANGE, type = "topic"),
                    value = @Queue(TOPIC_QUEUE1),
                    key = {TOPIC_KEY1})
    })
    public void receive1(String msg) {
        System.out.println("[1]---receive msg：" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = TOPIC_EXCHANGE, type = "topic"),
                    value = @Queue(TOPIC_QUEUE2),
                    key = {TOPIC_KEY2}
            )
    })
    public void receive2(String msg) {
        System.out.println("[2]---receive msg：" + msg);
    }
}

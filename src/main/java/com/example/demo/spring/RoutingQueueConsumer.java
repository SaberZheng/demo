package com.example.demo.spring;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.*;

/**
 * @author Amy
 * @date 2020/8/1 15:15
 */
@Component
public class RoutingQueueConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = ROUTING_EXCHANGE),
                    value = @Queue(ROUTING_QUEUE1),
                    key = {ROUTING_KEY1})
    })
    public void receive1(String msg) {
        System.out.println("[1]---receive msg：" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = ROUTING_EXCHANGE),
                    value = @Queue(ROUTING_QUEUE2),
                    key = {ROUTING_KEY1, ROUTING_KEY2}
            )
    })
    public void receive2(String msg) {
        System.out.println("[2]---receive msg：" + msg);
    }
}

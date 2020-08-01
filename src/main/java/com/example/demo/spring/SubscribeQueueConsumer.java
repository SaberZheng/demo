package com.example.demo.spring;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.demo.MqConstants.*;

/**
 * @author Amy
 * @date 2020/8/1 14:39
 */
@Component
public class SubscribeQueueConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = SUBSCRIBE_EXCHANGE, type = "fanout"),
                    value = @Queue(SUBSCRIBE_QUEUE1))
    })
    public void receive1(String msg) {
        System.out.println("[1]---receive msg：" + msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = SUBSCRIBE_EXCHANGE, type = "fanout"),
                    value = @Queue(SUBSCRIBE_QUEUE2))
    })
    public void receive2(String msg) {
        System.out.println("[2]---receive msg：" + msg);
    }
}

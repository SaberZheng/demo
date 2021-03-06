package com.example.demo.rabbitmq.routing;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:33
 */
public class RoutingQueueConsumer2 {

    private static final String QUEUE_NAME = "test.routing.queue2";

    private static final String EXCHANGE_NAME = "test.routing.exchange";

    private static final String ROUTING1_KEY = "test.routing1";

    private static final String ROUTING2_KEY = "test.routing2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING1_KEY);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING2_KEY);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("[2]---receive msg：" + message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}

package com.example.demo.rabbitmq.subscribe;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:33
 */
public class SubscribeQueueConsumer2 {

    private static final String QUEUE_NAME = "test.subscribe.queue2";

    private static final String EXCHANGE_NAME = "test.subscribe.exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body);
                System.out.println("[2]---receive msg：" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

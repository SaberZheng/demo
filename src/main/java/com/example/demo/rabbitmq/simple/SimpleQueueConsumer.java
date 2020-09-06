package com.example.demo.rabbitmq.simple;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:33
 */
public class SimpleQueueConsumer {

    private static final String QUEUE_NAME = "test.simple.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body);
                System.out.println("receive msg：" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

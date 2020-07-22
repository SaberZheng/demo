package com.example.demo.work;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:33
 */
public class WorkQueueConsumer2 {

    private static final String QUEUE_NAME = "test.work.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body);
                System.out.println("[1]---receive msg：" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

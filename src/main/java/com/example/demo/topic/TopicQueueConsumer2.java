package com.example.demo.topic;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/23 13:29
 */
public class TopicQueueConsumer2 {

    private final static String EXCHANGE_NAME = "test.topic.exchange";

    private final static String QUEUE_NAME = "test.topic.queue2";

    private static final String ROUTING_KEY = "test.#";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                String message = new String(body);
                System.out.println("[2]---receive msg：" + message);
            }
        };
        channel.basicConsume(QUEUE_NAME, consumer);
    }
}

package com.example.demo.rabbitmq.topic;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/23 13:23
 */
public class TopicQueueProducer {

    private static final String EXCHANGE_NAME = "test.topic.exchange";

    private static final String ROUTING1_KEY = "test.topic.publish";

    private static final String ROUTING2_KEY = "test.topic.subscribe";

    private static final String ROUTING3_KEY = "test.publish";

    private static final String ROUTING4_KEY = "topic.topic.subscribe";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String s1 = "1 hello test";
        System.out.println("send msg：" + s1);
        channel.basicPublish(EXCHANGE_NAME, ROUTING1_KEY, null, s1.getBytes());

        String s2 = "2 hello subscribe";
        System.out.println("send msg：" + s2);
        channel.basicPublish(EXCHANGE_NAME, ROUTING2_KEY, null, s2.getBytes());

        String s3 = "3 hello publish";
        System.out.println("send msg：" + s3);
        channel.basicPublish(EXCHANGE_NAME, ROUTING3_KEY, null, s3.getBytes());

        String s4 = "4 hello topic";
        System.out.println("send msg：" + s4);
        channel.basicPublish(EXCHANGE_NAME, ROUTING4_KEY, null, s4.getBytes());

        channel.close();
        connection.close();
    }
}

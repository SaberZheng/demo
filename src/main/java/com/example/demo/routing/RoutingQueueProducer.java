package com.example.demo.routing;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.rabbitmq.client.BuiltinExchangeType.DIRECT;

/**
 * @author Amy
 * @date 2020/7/19 19:34
 */
public class RoutingQueueProducer {
    private static final String EXCHANGE_NAME = "test.routing.exchange";

    private static final String ROUTING1_KEY = "test.routing1";

    private static final String ROUTING2_KEY = "test.routing2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, DIRECT);
        String s1 = "1 hello world";
        System.out.println("send msg：" + s1);
        channel.basicPublish(EXCHANGE_NAME, ROUTING1_KEY, null, s1.getBytes());
        String s2 = "2 hello world";
        System.out.println("send msg：" + s2);
        channel.basicPublish(EXCHANGE_NAME, ROUTING2_KEY, null, s2.getBytes());
        channel.close();
        connection.close();
    }
}

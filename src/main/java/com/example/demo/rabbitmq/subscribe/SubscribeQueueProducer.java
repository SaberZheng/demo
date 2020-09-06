package com.example.demo.rabbitmq.subscribe;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.rabbitmq.client.BuiltinExchangeType.FANOUT;

/**
 * @author Amy
 * @date 2020/7/19 19:34
 */
public class SubscribeQueueProducer {

    private static final String EXCHANGE_NAME = "test.subscribe.exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, FANOUT);
        String s = "hello world";
        System.out.println("send msg：" + s);
        channel.basicPublish(EXCHANGE_NAME, "", null, s.getBytes());
        channel.close();
        connection.close();
    }
}

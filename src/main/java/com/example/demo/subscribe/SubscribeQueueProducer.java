package com.example.demo.subscribe;

import com.example.demo.utills.ConnectionUtils;
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

    private static final String QUEUE1_NAME = "test.subscribe.queue1";

    private static final String QUEUE2_NAME = "test.subscribe.queue2";

    private static final String EXCHANGE_NAME = "test.subscribe.exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, FANOUT);
        channel.queueDeclare(QUEUE1_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE2_NAME, true, false, false, null);
        channel.queueBind(QUEUE1_NAME, EXCHANGE_NAME, "");
        channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, "");
        String s = "hello world";
        System.out.println("send msg：" + s);
        channel.basicPublish(EXCHANGE_NAME, "", null, s.getBytes());
        channel.close();
        connection.close();
    }
}

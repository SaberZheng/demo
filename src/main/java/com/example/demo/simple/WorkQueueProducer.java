package com.example.demo.simple;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:34
 */
public class WorkQueueProducer {

    private static final String QUEUE_NAME = "test.simple.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String s = "hello world";
        System.out.println("send msg：" + s);
        channel.basicPublish("", QUEUE_NAME, null, s.getBytes());
        channel.close();
        connection.close();
    }
}

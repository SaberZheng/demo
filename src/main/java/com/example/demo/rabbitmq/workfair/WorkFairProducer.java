package com.example.demo.rabbitmq.workfair;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/19 19:34
 */
public class WorkFairProducer {

    private static final String QUEUE_NAME = "test.work.fair.queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //保证一次只发送一个消息给同一个消费者
        channel.basicQos(1);
        for (int i = 0; i < 50; i++) {
            String s = String.format("%d hello world", i);
            System.out.println("send msg：" + s);
            channel.basicPublish("", QUEUE_NAME, null, s.getBytes());
            Thread.sleep(500);
        }
        channel.close();
        connection.close();
    }
}

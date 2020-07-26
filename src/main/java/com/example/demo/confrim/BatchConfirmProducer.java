package com.example.demo.confrim;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/25 15:10
 */
public class BatchConfirmProducer {

    private static final String QUEUE_NAME = "test.batch.queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //开启confirm模式
        channel.confirmSelect();

        String s = "hello world";
        for (int i = 0; i < 10; i++) {
            System.out.println("send msg：" + s);
            channel.basicPublish("", QUEUE_NAME, null, s.getBytes());
        }


        if (!channel.waitForConfirms()) {
            System.out.println("send msg failed");
        } else {
            System.out.println("send msg successful");
        }

        channel.close();
        connection.close();

    }
}

package com.example.demo.rabbitmq.confrim;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/25 15:10
 */
public class SingleConfirmProducer {

    private static final String QUEUE_NAME = "test.single.queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String s = "hello world";
        System.out.println("send msg：" + s);
        //开启confirm模式
        channel.confirmSelect();
        channel.basicPublish("", QUEUE_NAME, null, s.getBytes());

        if (!channel.waitForConfirms()) {
            System.out.println("send msg failed");
        }else{
            System.out.println("send msg successful");
        }

        channel.close();
        connection.close();

    }
}

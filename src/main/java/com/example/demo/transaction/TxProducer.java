package com.example.demo.transaction;

import com.example.demo.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/25 15:10
 */
public class TxProducer {

    private static final String QUEUE_NAME = "test.tx.queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String s = "hello world";
        System.out.println("send msg：" + s);
        try {
            channel.txSelect();
            int i = 1/0;
            channel.basicPublish("", QUEUE_NAME, null, s.getBytes());
            channel.txCommit();
        } catch (Exception e) {
            System.out.println("rollback msg:" + s);
            channel.txRollback();
        } finally {
            channel.close();
            connection.close();
        }
    }
}

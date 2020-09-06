package com.example.demo.rabbitmq.confrim;

import com.example.demo.rabbitmq.utills.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

/**
 * @author Amy
 * @date 2020/7/26 11:17
 */
public class AsyncConfirmProducer {

    private static final String QUEUE_NAME = "test.async.queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //开启confirm模式
        channel.confirmSelect();

        //未确认的消息标识
        SortedSet<Long> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        //通道增加监听
        ConfirmListener confirmListener = new ConfirmListener() {
            //成功了清理标识
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                if (multiple) {
                    System.out.println(deliveryTag + "、send multiple msg successful");
                    sortedSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println(deliveryTag + "、send msg successful");
                    sortedSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                System.out.println("send msg failed");
            }
        };

        channel.addConfirmListener(confirmListener);

        String s = "hello async";
        for (int i = 0; i < 10; i++) {
            long nextPublishSeqNo = channel.getNextPublishSeqNo();
            String msg = nextPublishSeqNo + "、" + s;
            System.out.println("send msg：" + msg);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            sortedSet.add(nextPublishSeqNo);
        }
        channel.close();
        connection.close();
    }
}

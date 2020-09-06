package com.example.demo.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Amy
 * @date 2020/7/26 23:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpringRabbitMQ {

    @Autowired
    private SimpleQueueProducer simpleQueueProducer;

    @Autowired
    private WorkQueueProducer workQueueProducer;

    @Autowired
    private SubscribeQueueProducer subscribeQueueProducer;

    @Autowired
    private RoutingQueueProducer routingQueueProducer;

    @Autowired
    private TopicQueueProducer topicQueueProducer;

    @Test
    public void testSimpleQueue() {
        simpleQueueProducer.send();
    }

    @Test
    public void testWorkQueue() {
        workQueueProducer.send();
    }

    @Test
    public void testSubscribeQueue() {
        subscribeQueueProducer.send();
    }

    @Test
    public void testRoutingQueue() {
        routingQueueProducer.send();
    }

    @Test
    public void testTopicQueue() {
        topicQueueProducer.send();
    }
}

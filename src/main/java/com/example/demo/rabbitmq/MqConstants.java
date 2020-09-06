package com.example.demo.rabbitmq;

/**
 * @author Amy
 * @date 2020/7/26 23:15
 */
public interface MqConstants {

    /*--- queue --*/

    String SIMPLE_QUEUE = "spring.simple.queue";

    String WORK_QUEUE = "spring.work.queue";

    String SUBSCRIBE_QUEUE1 = "spring.subscribe.queue1";

    String SUBSCRIBE_QUEUE2 = "spring.subscribe.queue2";

    String ROUTING_QUEUE1 = "spring.routing.queue1";

    String ROUTING_QUEUE2 = "spring.routing.queue2";

    String TOPIC_QUEUE1 = "spring.topic.queue1";

    String TOPIC_QUEUE2 = "spring.topic.queue2";

    /*--- exchange --*/

    String SUBSCRIBE_EXCHANGE = "spring.subscribe.exchange";

    String ROUTING_EXCHANGE = "spring.routing.exchange";

    String TOPIC_EXCHANGE = "spring.topic.exchange";

    /*--- routing key --*/

    String ROUTING_KEY1 = "spring.routing.key1";

    String ROUTING_KEY2 = "spring.routing.key2";

    String TOPIC_KEY1 = "spring.topic.*";

    String TOPIC_KEY2 = "spring.#";

    String TOPIC_KEY3 = "spring.topic.key";

    String TOPIC_KEY4 = "spring.topic";

}

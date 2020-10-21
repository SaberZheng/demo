package com.example.demo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //opsForValue操作string
        //opsForList list
        //opsForHyperLogLog HyperLogLog
        //除了基本的操作，常用的方法都可以直接通过RedisTemplate操作，比如事务
        //清空数据库通过connection操作
    /*RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
    connection.flushDb();*/

        redisTemplate.opsForValue().set("mykey", "关注狂神说Java的公众号");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }
}

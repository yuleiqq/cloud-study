package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
   private  RedisTemplate redisTemplate;


    @Autowired
    private StringRedisTemplate  stringRedisTemplate;


    @Test
    public void testStringSet(){

        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("name","yueli");

    }

}

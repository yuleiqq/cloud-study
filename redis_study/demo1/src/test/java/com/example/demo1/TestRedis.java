package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;

@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * ÓÃ»§»­Ïñ
     */

    @Test
    public void userProfile(){
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("user:tags:1");
        boundSetOperations.add("dd","cc","dd","mm");
        Set members = boundSetOperations.members();
        System.out.println(members);

    }


    @Test
    public void testStringGet(){

        ValueOperations valueOperations = redisTemplate.opsForValue();

        Object name = valueOperations.get("name");
        System.out.println(name);
    }


}

package com.example.demo1;

import com.example.demo1.vo.UserPointVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户画像
     */

    @Test
    public void userProfile(){
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("user:tags:1");
        boundSetOperations.add("dd","cc","dd","mm");
        Set members = boundSetOperations.members();
        System.out.println(members);

    }


    /**
     * 社交应用
     */

    @Test
    public void testSocial(){

        BoundSetOperations operationsLW = redisTemplate.boundSetOps("user:lw");
        operationsLW.add("A","B","C","D","E");

        System.out.println("老王的粉丝"+operationsLW.members());


        BoundSetOperations operationsXD = redisTemplate.boundSetOps("user:lw");
        operationsLW.add("F","G","C","D","E");

        System.out.println("小弟的粉丝"+operationsXD.members());


        Set lwSet = operationsLW.diff("user:xd");
        System.out.println("老王特有粉丝: "+lwSet);


    }


    /**
     * 用户积分榜单
     */

    @Test
    public void testData(){

        UserPointVO p1 = new UserPointVO("tom1","90091");
        UserPointVO p2 = new UserPointVO("tom2","90092");
        UserPointVO p3 = new UserPointVO("tom3","90092");
        UserPointVO p4 = new UserPointVO("tom4","90092");
        UserPointVO p5 = new UserPointVO("tom5","90092");
        UserPointVO p6 = new UserPointVO("tom6","90092");


        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("point:rank:real");

        boundZSetOperations.add(p1,12);
        boundZSetOperations.add(p2,123);
        boundZSetOperations.add(p3,1);
        boundZSetOperations.add(p4,5);
        boundZSetOperations.add(p5,6);
        boundZSetOperations.add(p6,0);


    }









}

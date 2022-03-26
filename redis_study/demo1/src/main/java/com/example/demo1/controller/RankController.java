package com.example.demo1.controller;


import com.example.demo1.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/rank")
public class RankController {


    @Autowired
    private RedisTemplate redisTemplate;
    private static  final String DAILY_RANK_KEY = "video:rank:daily";


    /**
     * 返回全部榜单，从大到小
     * @return
     */
    @RequestMapping("real_rank1")
    public JsonData realRank1(){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("point:rank:real");

        Set set = boundZSetOperations.reverseRange(0, -1);

        return  JsonData.buildSuccess(set);
    }

    /**
     * 返回全部榜单，从小到大
     * @return
     */
    @RequestMapping("real_rank2")
    public JsonData realRank2(){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("point:rank:real");

        Set set = boundZSetOperations.range(0, -1);

        return  JsonData.buildSuccess(set);
    }




}

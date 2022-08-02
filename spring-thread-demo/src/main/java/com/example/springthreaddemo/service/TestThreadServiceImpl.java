package com.example.springthreaddemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TestThreadServiceImpl {

    @Async    //使用@Async开启一个线程
    public Future<String> func01() throws Exception{
        int count =0;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("func01运行 = " +Thread.currentThread().getName() + i);

            count ++;
        }

        return  new AsyncResult<>("测试返回结果！" +count);
    }

    @Async
    public void func02() throws Exception {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1500);
            System.out.println("func02运行 = "+Thread.currentThread().getName() + i);
        }
    }

    @Async
    public void func03() throws Exception {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            System.out.println("func03运行 = " +Thread.currentThread().getName()+ i);
        }
    }

}

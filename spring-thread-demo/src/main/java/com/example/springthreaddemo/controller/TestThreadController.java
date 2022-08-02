package com.example.springthreaddemo.controller;

import com.example.springthreaddemo.service.TestThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class TestThreadController {

    @Autowired
    private TestThreadServiceImpl testThreadService;

    @RequestMapping(value = "/testThread")
    public void testThread(){

        try {

           long start = System.currentTimeMillis();

            Future result = testThreadService.func01();
            testThreadService.func02();
            testThreadService.func03();

            System.out.println("执行完毕！");

            long end  = System.currentTimeMillis();

            System.out.println("执行时长: "+ (end- start));


            System.out.println("返回结果: "+ result.get());


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
package com.example.springthreaddemo.controller;

import com.example.springthreaddemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/testThread")
    public void testThread(){

        try {

            long start = System.currentTimeMillis();
            Future result = helloService.func01();
            helloService.func02();
            helloService.func03();
            System.out.println("执行完毕！");
            long end  = System.currentTimeMillis();
            System.out.println("执行时长: "+ (end- start));
            System.out.println("返回结果: "+ result.get());

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @GetMapping("/get")
    public void get() throws Exception {

         //初始化一个集合
         List<String> list = Arrays.asList("0","1","2","3","4","5","6");

         long start =  System.currentTimeMillis();

         for(String cbiid : list){

             System.out.println(cbiid);

             try {
                 Future <String>  result  =  helloService.bill(cbiid);
                 //打印返回的异常信息
                 System.out.println("哈哈哈"+result.get());
             } catch (Exception e) {
                 System.out.println("客户 "+cbiid +" 异常信息："+e.getMessage() );
             }
         }

        long end =  System.currentTimeMillis();

        System.out.println(end -start);


    }




}
package com.example.oauth2server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yulei
 * @create: 2022-11-24
 * @Version 1.0
 **/


@RestController
@RequestMapping("/test")
public class HelloController {


    @RequestMapping("/hello")
    public String   hello(){

        System.out.println("你和奥");

        return "success";

    }


}

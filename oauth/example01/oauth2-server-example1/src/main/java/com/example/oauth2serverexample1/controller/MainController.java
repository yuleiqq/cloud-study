package com.example.oauth2serverexample1.controller;


import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 一些普通的接口
 */
@Profile("simple")
@RestController
public class MainController {


    @GetMapping("/")
    public String email(){

        return "这是主页";
    }

    @GetMapping("/admin")
    public String admin(){

        return "这是admin页";
    }


    @GetMapping("/user")
    public String user(){

        return "这是user页";
    }



}

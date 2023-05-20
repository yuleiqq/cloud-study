package com.example.oauth2server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: yulei
 * @create: 2022-12-01
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request){

//        HttpSession session = request.getSession();
//        Object userName = session.getAttribute("userName");
//        System.out.println(userName.toString());

        System.out.println("一个小测试");
        return "test";
    }

}

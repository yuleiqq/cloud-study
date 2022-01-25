package com.example.oauth2serverexample1.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * 获得认证信息，当认证通过后，第三方应用可以请求的资源
 */
@Profile("simple")
@RestController
public class ResourceController {

    private static  final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    /**
     * 此处提供了用户信息端点 /me ，供OAuth 客户端获取认证对象时调用
     *
     *
     * 过滤器： OAuth2AuthenticationProcessingFilter
     *
     */
    @RequestMapping("/me")
    public Principal me(Principal principal){

        logger.debug(principal.toString());
        return  principal;

    }

    @GetMapping("/phone")
    public String phone(){
        return "phone: 1234567890";
    }




}

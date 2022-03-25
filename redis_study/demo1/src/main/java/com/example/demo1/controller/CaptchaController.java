package com.example.demo1.controller;

import com.example.demo1.util.CommonUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class CaptchaController {

    @Autowired
    Producer captchaProducer;

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("get_captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String captcahText = captchaProducer.createText();



    }

    /**
     * redis 中， 保存验证码的key
     * @param request
     * @return
     */

    private String getCaptchaKey (HttpServletRequest request){

        String ip = CommonUtils.getIpAddr(request);

        String userAgent = request.getHeader("User-Agent");

        String key = "user-service:captcha:"+CommonUtils.MD5(ip+userAgent);

        return  key ;
    }


}

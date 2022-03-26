package com.example.demo1.controller;

import com.example.demo1.util.CommonUtils;
import com.example.demo1.util.JsonData;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/captcha")
public class CaptchaController {

    @Autowired
    Producer captchaProducer;

    @Autowired
    StringRedisTemplate redisTemplate;


    @GetMapping("get_captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String captcahText = captchaProducer.createText();

        String key = getCaptchaKey(request);

        //10 分钟过期
        redisTemplate.opsForValue().set(key,captcahText, 10,TimeUnit.MINUTES);

        //图片返回到前端
        BufferedImage image = captchaProducer.createImage(captcahText);
        ServletOutputStream outputStream = null;
        try {
             outputStream = response.getOutputStream();
             ImageIO.write(image,"jpg",outputStream);
             outputStream.flush();
             outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 发送验证码
     * @param to
     * @param captcha
     * @param request
     * @return
     */

    @GetMapping("send_code")
    public JsonData  sendCode(@RequestParam(value = "to",required = true) String to ,
                             @RequestParam(value = "captcha",required = true) String captcha ,
                             HttpServletRequest request){

         String key = getCaptchaKey(request);

        System.out.println("进入获取图片验证码");

        String cacheCaptcha = redisTemplate.opsForValue().get(key);


        if(captcha!=null && cacheCaptcha!=null && cacheCaptcha.equalsIgnoreCase(captcha)){

            redisTemplate.delete(key);
            //TODO 发送验证码

            return JsonData.buildSuccess();
        }else
        {
            return JsonData.buildError("验证码错误");
        }

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

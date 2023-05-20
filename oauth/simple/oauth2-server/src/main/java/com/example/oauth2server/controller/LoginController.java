package com.example.oauth2server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: yulei
 * @create: 2022-11-24
 * @Version 1.0
 **/


@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;


//    /**
//     * 自定义登录接口
//     * @param user
//     * @return
//     */
//    @RequestMapping("/doLogin")
//    public Object    hello(@RequestBody User user){
//
//        System.out.println(user.toString());
//        String username = user.getUserName();
//        String password = user.getPassWord();
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        ResponeData  responeData =null;
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            boolean authenticated = authentication.isAuthenticated();
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            if(authenticated){
//
//                responeData   =   ResponeData.buildSuccess("登录成功");
//            }else {
//
//                responeData =  ResponeData.buildError("登录失败");
//            }
//
//        }catch (Exception ex ){
//             responeData =  ResponeData.buildError("登录异常: "+ ex.getMessage());
//
//        }
//        finally {
//             return  responeData;
//        }
//








}

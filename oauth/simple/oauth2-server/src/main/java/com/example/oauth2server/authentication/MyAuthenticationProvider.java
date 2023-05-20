//package com.example.oauth2server.authentication;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Component;
//
///**
// * @author: yulei
// * @create: 2022-11-30
// * @Version 1.0
// **/
//@Component
//public class MyAuthenticationProvider extends DaoAuthenticationProvider {
//
//
//    /**
//     * 通过构造方法进行注入
//     * @param userDetailsService
//     * @param passwordEncoder
//     */
//
//    public MyAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.setUserDetailsService(userDetailsService);
//        this.setPasswordEncoder(passwordEncoder);
//    }
//
//
////    @Bean
////    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
////        return new InMemoryUserDetailsManager();
////    }
//
//}

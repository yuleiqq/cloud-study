package com.example.oauth2server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.oauth2server.mapper")
public class ServerSimple {

    public static void main(String[] args) {
        SpringApplication.run(ServerSimple.class, args);
    }

}

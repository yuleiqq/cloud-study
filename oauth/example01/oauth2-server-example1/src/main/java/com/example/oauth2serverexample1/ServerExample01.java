package com.example.oauth2serverexample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

//@Profile("minimal")
@Profile("simple")
@SpringBootApplication
//@EnableAuthorizationServer
public class ServerExample01 {

    public static void main(String[] args) {
        SpringApplication.run(ServerExample01.class, args);
    }

}

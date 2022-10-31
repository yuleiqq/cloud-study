package com.example.camundademo1;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamundaDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(CamundaDemo1Application.class, args);
    }

}

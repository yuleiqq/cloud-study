package com.example.rabbitmq.pub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


/**
 * 轮训策略
 */
public class Send {

    private final static  String EXCHANGE_NAME = "exchange_fanout";

    public static void main(String[] args) throws Exception {

        ConnectionFactory  factory = new ConnectionFactory();

        factory.setHost("8.140.98.27");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        try(Connection connection = factory.newConnection();

            //创建信道
            Channel channel = connection.createChannel()) {
            //绑定交换机，即 fanout扇形，即广播
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String msg = "发布订阅模式";
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("广播消息发送成功!");

            }
        }
    }


package com.example.rabbitmq.work.rr;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv2 {
    private final static String QUEUE_NAME = "work_qq_rr";

    public static void main(String[] argv)
            throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("8.140.98.27");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/");
        factory.setPort(5672);
       //消费者一般不增加自动关闭
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //队列的声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.To exit press CTRL + C");
        //回调方法，下面两种都行
        Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                  // consumerTag 是固定的 可以做此会 话的名字， deliveryTag 每次接收消息+1
                    System.out.println("consumerTag 消息标识=" + consumerTag);
                    //可以获取交换机，路由健等
                    System.out.println("envelope元数 据 = "+envelope);
                    System.out.println(" properties 配置信息=" + properties);
                    System.out.println("body=" + new String(body, "utf-8"));
                    //手工确认消息，不是多条确认
                    channel.basicAck(envelope.getDeliveryTag(),false);

                }
    };
        //关闭消息自动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);
        }
    }
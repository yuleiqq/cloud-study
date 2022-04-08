package com.example.rabbitmq.topic;

import com.rabbitmq.client.*;

public class Recv2 {

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("8.140.98.27");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        //消费者一般不增加自动关闭
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
       //绑定交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
       //获取队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定队列和交换机，第一个节点
        channel.queueBind(queueName,EXCHANGE_NAME,"order.log.error");
        DeliverCallback deliverCallback =  (consumerTag, delivery) -> {
            String message = new  String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
      };
       //自动确认消息
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });


} }
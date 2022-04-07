package com.example.rabbitmq.pub;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv1 {
    private final static  String EXCHANGE_NAME = "exchange_fanout";

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

            //绑定交换机
            channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.FANOUT);
            //获取队列
            String queue = channel.queueDeclare().getQueue();

            //绑定交换机和队列， fanout 是不用绑定routing key 的
            channel.queueBind(queue, EXCHANGE_NAME,"");


        //回调方法，下面两种都行
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body=" + new String(body, "utf-8"));
                //手工确认消息，不是多条确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };
        //关闭消息自动确认
        channel.basicConsume(queue, false, consumer);

       }
}
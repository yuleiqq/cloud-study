package com.example.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Send {
    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv)
            throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("8.140.98.27");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        /**
         * 消息生产者不用过多操作，只需要和交换机绑定
         */
        try (//创建连接
             Connection connection =
                     factory.newConnection(); //创建信道
             Channel channel =
                     connection.createChannel()) {
            //绑定交换机,直连交换机即可
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String error = "我是订单错误日志";
            String info = "我是订单info日志";
            String debug = "我是商品debug日志";
            channel.basicPublish(EXCHANGE_NAME, "order.log.error", null, error.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, "order.log.info", null, info.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME, "product.log.debug", null, debug.getBytes(StandardCharsets.UTF_8));




        }
    }

}
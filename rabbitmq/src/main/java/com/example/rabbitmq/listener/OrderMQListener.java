package com.example.rabbitmq.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "order_queue")
public class OrderMQListener {

    @RabbitHandler
    public void messageHandler(String body, Message message, Channel channel) throws IOException {

        long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag = "+ msgTag);
        System.out.println("message = "+ message.toString());
        System.out.println("body = "+ body);

        //告诉broker ，消息已经被确认
        channel.basicAck(msgTag,true);
        //告诉broker，消息拒绝确认，重新入队列
        // channel.basicNack(msgTag,false,true);

    }

}

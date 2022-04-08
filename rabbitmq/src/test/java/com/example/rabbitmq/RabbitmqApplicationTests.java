package com.example.rabbitmq;

import com.example.rabbitmq.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){

           rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"order.new","新订单");
    }


    /**
     * 生产者到交换机可靠性投递测试
     */
    @Test
    public void testConfirmCallback(){

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             * @param correlationData
             * @param ack  交换机是否收到消息
             * @param cause  失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

                System.out.println("ack = "+ack);
                System.out.println("cause = "+ cause);

                if(ack){
                    System.out.println("发送成功 ! ");
                }else{
                    System.out.println("发送失败 ! ");
                }
             }
        });

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"order.new","新订单");

    }


    /**
     * 交换机到队列的可靠性投递测试
     */

    @Test
    void testReturnCallback() {
            //为true,则交换机处理消息到路由失败，则会返回给生 产者
            //开启强制消息投递(mandatory为设置为true)，但消息未被路由至任何一个queue，则回退一条消息
          rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
                                                @Override
                                                public void
                                                returnedMessage(ReturnedMessage returned) {
                                                    int code = returned.getReplyCode();
                                                    System.out.println("code="+code);
                                                    System.out.println("returned="+returned.toString());
                                                } });
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"order.new","新订单来啦11");
    }

}

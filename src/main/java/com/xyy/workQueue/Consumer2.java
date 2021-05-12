package com.xyy.workQueue;

import com.rabbitmq.client.*;
import com.xyy.util.RabbitmqFactory;

import java.io.IOException;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 17:33
 **/
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitmqFactory.getConnection();
        final Channel channel = connection.createChannel();
        //每次消费一个
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        /**
         * 消费消息
         * 1.队列名
         * 2.开启消息自动确认机制 true表示消费者自动向rabbitmq确认消息消费  false 不会自动确认消息
         * 3.回调
         *
         */
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body):" + new String(body));
                /**
                 * 消息确认
                 * 1.消息标志
                 * 2.是否同时确认多个  由于channel.basicQos(1)，所以为false
                 */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}

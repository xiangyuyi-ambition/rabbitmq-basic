package com.xyy.direct;

import com.rabbitmq.client.*;
import com.xyy.util.RabbitmqFactory;

import java.io.IOException;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-09 12:57
 **/
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitmqFactory.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs_direct","error");
        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1: " + new String(body));
            }
        });

    }


}

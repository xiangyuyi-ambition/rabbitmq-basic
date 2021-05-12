package com.xyy.topic;

import com.rabbitmq.client.*;
import com.xyy.util.RabbitmqFactory;

import java.io.IOException;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-09 13:39
 **/
public class Consumer2 {
    public static void main(String[] args) throws Exception {
       Connection connection =  RabbitmqFactory.getConnection();
       Channel channel = connection.createChannel();
       channel.exchangeDeclare("topics","topic");
       String queue = channel.queueDeclare().getQueue();
       channel.queueBind(queue, "topics", "user.#");
       channel.basicConsume(queue, true, new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               System.out.println("Consumer2 " + new String(body));
           }
       });
    }
}

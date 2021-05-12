package com.xyy.fanout;

import com.rabbitmq.client.*;
import com.xyy.util.RabbitmqFactory;

import java.io.IOException;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 19:18
 **/
public class Consumer3 {
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitmqFactory.getConnection();
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //将临时队列绑定exchange
        channel.queueBind(queue,"logs","");
        //处理消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3: "+new String(body));
            }
        });
    }
}

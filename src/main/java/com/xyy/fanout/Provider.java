package com.xyy.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xyy.util.RabbitmqFactory;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 19:18
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitmqFactory.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare("logs","fanout");//广播 一条消息多个消费者同时消费
        // 发布消息
        channel.basicPublish("logs","",null,"hello".getBytes());
        RabbitmqFactory.close(channel,connection);
    }
}

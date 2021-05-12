package com.xyy.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xyy.util.RabbitmqFactory;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-09 13:31
 **/
public class Provider {
    public static void main(String[] args)throws Exception {
        Connection connection = RabbitmqFactory.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String routr_key = "user.save.ss";
        channel.basicPublish("topics",routr_key,null, ("topic动态路由,route_key: " + routr_key).getBytes());
        RabbitmqFactory.close(channel, connection);
    }
}

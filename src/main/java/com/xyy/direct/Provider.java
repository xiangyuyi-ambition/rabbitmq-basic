package com.xyy.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xyy.util.RabbitmqFactory;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-09 12:37
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitmqFactory.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String routeKey = "ssd";
        channel.basicPublish("logs_direct",routeKey,null, ("direct route " + routeKey).getBytes());
        RabbitmqFactory.close(channel,connection);
    }
}

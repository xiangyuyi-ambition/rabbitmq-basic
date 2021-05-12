package com.xyy.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 16:36
 **/
public class RabbitmqFactory {

    private  static  ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        //ip地址
        connectionFactory.setHost("192.168.158.132");
        //端口号
        connectionFactory.setPort(5672);
        //虚拟机
        connectionFactory.setVirtualHost("/");
        //用户
        connectionFactory.setUsername("guest");
        //密码
        connectionFactory.setPassword("guest");
    }

    public static Connection getConnection(){
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Channel channel, Connection connection){
        try {
            if(channel != null){
                channel.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

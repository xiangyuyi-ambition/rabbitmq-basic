package com.xyy.workQueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.xyy.util.RabbitmqFactory;
import org.junit.Test;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 17:26
 **/
public class Provider {


    //生产消息
    @Test
    public void sendMessage() throws Exception {
        //获取连接对象
        Connection connection = RabbitmqFactory.getConnection();
        //获取通过
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for(int i=0; i< 20; i++){
            channel.basicPublish("","work", null, ("work queue" + i).getBytes());
        }
        RabbitmqFactory.close(channel,connection);
    }
}

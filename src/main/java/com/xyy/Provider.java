package com.xyy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.xyy.util.RabbitmqFactory;
import org.junit.Test;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 14:31
 **/
public class Provider {

    //生产消息
    @Test
    public void sendMessage() throws Exception{
        //获取连接对象
        Connection connection = RabbitmqFactory.getConnection();
        //获取通过
        Channel channel = connection.createChannel();
        /**
         * 1.队列名称
         * 2.是否为持久化队列,false队列随着服务关闭而丢失
         * 3.是否独占队列，既当前连接可用
         * 4.是否在消费完成后自动删除队列
         * 5.附加参数
         */
        channel.queueDeclare("aa",true,false,true,null);
        /**
         * 发布消息
         * 1.交换机名称
         * 2.队列名称
         * 3.额外内容  MessageProperties.PERSISTENT_TEXT_PLAIN  消息队列的内容也会持久化
         * 4.消息具体内容
         */
        channel.basicPublish("", "aa", MessageProperties.PERSISTENT_TEXT_PLAIN, "Hello rabbit".getBytes());
        RabbitmqFactory.close(channel,connection);

    }
}

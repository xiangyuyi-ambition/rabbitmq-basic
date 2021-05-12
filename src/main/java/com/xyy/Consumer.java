package com.xyy;

import com.rabbitmq.client.*;
import com.xyy.util.RabbitmqFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @program: rabbitmq_day01
 * @description
 * @author: xiangyuyi
 * @create: 2021-05-08 15:39
 **/
public class Consumer {
    public static void main(String[] args) throws Exception {

        //获取连接对象
        Connection connection = RabbitmqFactory.getConnection();
        //获取通过
        Channel channel = connection.createChannel();
        /**
         * 1.队列名称
         * 2.是否为持久化队列
         * 3.是否独占队列，既当前连接可用
         * 4.是否在消费完成后自动删除队列
         * 5.附加参数
         */
        channel.queueDeclare("aa",true,false,true,null);

        /**
         * 消费消息
         * 1.队列名
         * 2.开启消息自动确认机制 true表示消费者自动向rabbitmq确认消息消费  false 不会自动确认消息
         * 3.回调
         *
         */
        channel.basicConsume("aa", true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body):" + new String(body));
            }
        });
        //channel.close();
        //connection.close();
    }
}

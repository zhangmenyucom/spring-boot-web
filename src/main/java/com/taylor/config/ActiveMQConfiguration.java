package com.taylor.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMQConfiguration {
    /**
     * 定义点对点队列
     *
     * @return
     */
    @Bean
    @Qualifier("taylorQueue")
    public Queue queue() {
        return new ActiveMQQueue("taylorQueue");
    }

    /**
     * 定义一个主题
     * @return
     */
    @Bean
    @Qualifier("taylorTopic")
    public Topic topic() {
        return new ActiveMQTopic("taylorTopic");
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {

        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //设置连接数
        factory.setConcurrency("3-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        return factory;

    }
    @Bean
    public ConnectionFactory connectionFactory(){

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("failover:(tcp://192.168.186.130:61616,tcp://192.168.186.129:61616)?initialReconnectDelay=1000");
        return connectionFactory;
    }
}
package com.taylor.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

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
    @Bean("taylorQueue")
    public Queue queue() {
        return new ActiveMQQueue("taylorQueue");
    }

    /**
     * 定义一个主题
     * @return
     */
    @Bean("taylorTopic")
    public Topic topic() {
        return new ActiveMQTopic("taylorTopic");
    }

}
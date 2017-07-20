package com.taylor.producer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;

@Component
@Qualifier("sendTopicMessage")
@Data
public class SendTopicMessageImpl implements SendMessage {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("taylorTopic")
    private Topic topic;

    @Override
    public void sendMessage(final String message) {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        System.out.println("发送主题消息成功");
    }
}
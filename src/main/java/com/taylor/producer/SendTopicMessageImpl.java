package com.taylor.producer;

import com.taylor.config.JmsTopicTemplate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;

@Component
@Qualifier("sendTopicMessage")
@Data
@EnableConfigurationProperties(JmsTopicTemplate.class)
public class SendTopicMessageImpl implements SendMessage {

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTopicTemplate jmsTemplate;

	@Autowired
	@Qualifier("taylorTopic")
	private Topic topic;

	@Override
	public void sendMessage(final String message) {
		jmsTemplate.send(topic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
        System.out.println("发送主题消息成功");
    }
}
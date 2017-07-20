package com.taylor.producer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

@Component
@Qualifier("sendQueueMessage")
@Data
public class SendQueueMessageImpl implements SendMessage {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("taylorQueue")
	private Queue queue;

	@Override
	public void sendMessage(final String message) {
		jmsTemplate.setPubSubDomain(false);
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
        System.out.println("发送队列消息成功");
    }
}
package com.ldy.shch91.activeMq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


public class QueueSender {

	private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);


	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
 
	//发送消息
	public void sendMessage(Destination destination,final String message) { 
		logger.info("QueueSender发送消息："+message);

		jmsQueueTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(message);
			}
		});
	}
}
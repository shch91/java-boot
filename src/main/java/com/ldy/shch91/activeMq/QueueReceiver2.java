package com.ldy.shch91.activeMq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueueReceiver2 implements MessageListener {


	private static final Logger logger = LoggerFactory.getLogger(QueueReceiver2.class);

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage) message;
		try {
			logger.info("QueueReceiver2接收到消息内容是：" + textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			logger.error("receive message error",e);
		}
	}
}
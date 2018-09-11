package com.ldy.shch91.activeMq;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicReceiver2 implements MessageListener {
 

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("TopicReceiver2接收到消息内容是：" + textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
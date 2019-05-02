package shch91.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
public class MyMessageListener implements MessageListener<String, String> {
  
    @Override//此方法处理消息
    public void onMessage(ConsumerRecord<String, String> data) {
        String topic = data.topic();//消费的topic
        log.info("-------------recieve message from {} topic-------------", topic);
        log.info("partition:{}", String.valueOf(data.partition()));//消费的topic的分区
        log.info("offset:{}", String.valueOf(data.offset()));//消费者的位置
        log.info("get message from {} topic : {}", topic, data.value());//接收到的消息
    }
}

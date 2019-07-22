package shch91.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j

public class MyMessageListener implements MessageListener<String, String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        String topic = data.topic();
        log.info("-------------recieve message from {} topic-------------", topic);
        log.info("partition:{} and offset:{}", data.partition(), data.offset());
        log.info("get message from {} topic : {}", topic, data.value());
    }
}

package shch91.service.kafka;



import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ProducerKafka {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @Scheduled(cron="0 */1 * * * ?")
    public void send() {
        for (int i = 0; i < 10; i++) {
            String sendMsg = "hello kafka " + DateUtil.formatAsDatetime(new Date());
            log.info(" ProducerKafka : " + sendMsg);
            kafkaTemplate.send("kafka",sendMsg);
        }
    }
}

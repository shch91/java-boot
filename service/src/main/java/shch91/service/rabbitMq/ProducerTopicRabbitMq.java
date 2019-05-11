package shch91.service.rabbitMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerTopicRabbitMq {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Scheduled(cron="0 */1 * * * ?")
    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        log.info("sender1 : " + msg1);
       rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);
        
        String msg2 = "I am topic.mesaages msg########";
        log.info("sender2 : " + msg2);
        rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

}
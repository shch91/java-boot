package shch91.service.rabbitMq;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerRabbitMq {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Scheduled(cron="0 */10 * * * ?")
    public void send() {
        for (int i = 0; i < 10; i++) {
            String sendMsg = "hello " + DateUtil.formatAsDatetime(new Date());
            log.info("Sender1 : " + sendMsg);
            rabbitTemplate.convertAndSend("hello", sendMsg);
        }
    }

}
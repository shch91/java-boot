package com.ldy.shch91.rabbitMq;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Scheduled(cron="0 */1 * * * ?")
    public void send() {
        for (int i = 0; i < 10; i++) {
            String sendMsg = "hello " + DateUtil.formatAsDatetime(new Date());
            log.info("Sender1 : " + sendMsg);
            rabbitTemplate.convertAndSend("hello", sendMsg);
        }
    }

}
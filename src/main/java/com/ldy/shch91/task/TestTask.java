package com.ldy.shch91.task;

import com.ldy.shch91.activeMq.QueueSender;
import com.ldy.shch91.activeMq.TopicSender;
import com.ldy.shch91.util.RandomUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;


@Component
public class TestTask {

    private static final Logger logger = LoggerFactory.getLogger(TestTask.class);


    //定义每三秒执行任务
    @Scheduled(fixedRate = 3000)
    //@Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        logger.info("task runed");
    }

    @Resource
    private QueueSender queueSender;

    @Resource
    private TopicSender topicSender;

    @Resource
    @Qualifier("queueDestination")
    private Destination queueDestination;

    @Resource
    @Qualifier("topicDestination")
    private Destination topicDestination;

    @Scheduled(cron = "0 */1 * * * ?")
    //@Scheduled(fixedRate = 3000)
    public void testSend() {

            queueSender.sendMessage(queueDestination, "queue生产者产生消息：" + RandomUtil.generateStr(10));


            topicSender.sendMessage(topicDestination, "topic生产者产生消息：" + RandomUtil.generateStr(5));

    }


}
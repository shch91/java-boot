package com.ldy.shch91.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TestTask {

    private static   final Logger logger=LoggerFactory.getLogger(TestTask.class);


    //定义每三秒执行任务
    //@Scheduled(fixedRate=3000)
    @Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        logger.info("task runed");
    }
 
}
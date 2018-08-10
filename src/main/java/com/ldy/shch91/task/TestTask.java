package com.ldy.shch91.task;


import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    private   final Logger logger=LoggerFactory.getLogger(getClass());


    //定义每三秒执行任务
//  @Scheduled(fixedRate=3000)
    @Scheduled(cron="4-10 * * * * ?")
    public void reportCurrentTime() {
        logger.info("task runed");
    }
 
}
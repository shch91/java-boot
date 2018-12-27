package com.ldy.shch91.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {


    //定义每三秒执行任务
    //@Scheduled(fixedRate = 3000)
    //@Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        log.info("task runed");
    }

    private static final String test="XXX";
}
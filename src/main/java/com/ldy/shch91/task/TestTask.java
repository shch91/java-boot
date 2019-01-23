package com.ldy.shch91.task;

import com.ldy.shch91.remote.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class TestTask {

    private static final String test="HHHHH";

    @Autowired
    @Qualifier(value="localDemoService")
    DemoService  localDemoService;


    private static  final Random rand=new Random();
    //定义每三秒执行任务
    @Scheduled(fixedRate = 3000)
    @Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        log.info("task runed");
        Integer a=rand.nextInt(134321);
        String ret=localDemoService.sayHello(a.toString());
        log.info(ret);
    }


}
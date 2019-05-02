package shch91.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import shch91.inter.DemoService;

import java.util.Random;

@Slf4j
@Component
public class TestTask {

    private static final String test="HHHHH";

    @Autowired
    DemoService demoService;


    private static  final Random rand=new Random();
    //定义每三秒执行任务
    //@Scheduled(fixedRate = 3000)
    @Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        log.info("task runed");
        Integer a=rand.nextInt(134321);
        String ret=demoService.sayHello(a.toString());
        log.info(ret);
    }


}
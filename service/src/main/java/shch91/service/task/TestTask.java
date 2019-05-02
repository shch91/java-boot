package shch91.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class TestTask {

    private static final String test="HHHHH";



    private static  final Random rand=new Random();
    //定义每三秒执行任务
    //@Scheduled(fixedRate = 3000)
    //@Scheduled(cron="0 */1 * * * ?")
    public void reportCurrentTime() {
        log.info("task runed");
        Integer a=rand.nextInt(134321);

        log.info("value="+a);
    }


}
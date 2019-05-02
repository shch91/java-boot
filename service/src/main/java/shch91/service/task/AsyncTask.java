package shch91.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

@Component
@Slf4j
public class AsyncTask {


    @Async
    public Future<Boolean> dotask(){

        log.info("执行异步任务结束");
        return new AsyncResult<Boolean>(true);
    }
 
}
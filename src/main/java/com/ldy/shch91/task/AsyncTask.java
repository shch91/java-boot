package com.ldy.shch91.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTask {

    private  static final Logger logger= LoggerFactory.getLogger(AsyncTask.class);
      
    @Async
    public Future<Boolean> dotask(){

        logger.info("执行异步任务结束");
        return new AsyncResult<Boolean>(true);
    }
 
}
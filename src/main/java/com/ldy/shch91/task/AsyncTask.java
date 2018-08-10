package com.ldy.shch91.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import java.util.concurrent.Future;

public class AsyncTask {
 
    @Async
    public Future<Boolean> dotask(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行异步任务结束");
        return new AsyncResult<>(true);
    }
 
}
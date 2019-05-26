package shch91.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
 
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
 
/**
 * 异步任务的控制器
 *
 **/
@RestController
@Slf4j
public class AsyncController {
 
    private BlockingQueue<DeferredResult<String>> blockingQueue = new ArrayBlockingQueue(1024);
 
    /**
     * 返回值是DeferredResult类型，如果没有结果请求阻塞
     *
     * @return
     */
    @GetMapping("/quotes")
    public DeferredResult<String> quotes() {
        //指定超时时间，及出错时返回的值
        DeferredResult<String> result = new DeferredResult(3000L,"error");
        blockingQueue.add(result);
        return result;
    }
 
    /**
     * 另外一个请求(新的线程)设置值
     *
     * @throws InterruptedException
     */
 
    @GetMapping("/take")
    public void take() throws InterruptedException {
        DeferredResult<String> result = blockingQueue.take();
        result.setResult("route");
    }
 
    @GetMapping
    public Callable<String> callable() {
        return () -> "callable";
    }
 


}

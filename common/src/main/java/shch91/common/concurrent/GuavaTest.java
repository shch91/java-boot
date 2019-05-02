package shch91.common.concurrent;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;

import java.util.concurrent.Executors;

public class GuavaTest {


    public void fd() {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        final ListenableFuture<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * 2);
                return "Task done !";
            }
        });


        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        },MoreExecutors.sameThreadExecutor());



        pool.shutdown();
    }
}

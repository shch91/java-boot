package com.ldy.shch91.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;

import java.util.concurrent.Executors;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileNio {
    @Test
    public  void fds() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/shch/redis-start.sh", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
    @Test
    public void fd() throws InterruptedException {
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

        Thread.sleep(5*1000);

        pool.shutdown();
    }

}

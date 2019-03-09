package com.ldy.shch91.test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceShutDownTest {

    public static void main(String[] args) throws InterruptedException {

        class MyThread implements Runnable {

            public void run() {
                try {
                    Random random = new Random();
                    int time = 1000 * (random.nextInt(5) + 1);
                    Thread.sleep(time);
                    System.out.println(Thread.currentThread().getName() + " complete , time = " + time);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " Interrupted!");
                }
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new MyThread());
        executorService.submit(new MyThread());
        executorService.submit(new MyThread());
        executorService.submit(new MyThread());

        Thread.sleep(2000);

        //        executorService.shutdown();
        List<Runnable> list = executorService.shutdownNow();
        System.out.println(list.size());


    }

}
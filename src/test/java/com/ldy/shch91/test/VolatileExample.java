package com.ldy.shch91.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VolatileExample {

    private static Logger logger = LoggerFactory.getLogger(TestFastJson.class);


    private int a = 0;
    private volatile boolean flag = false;

    public CountDownLatch latch = new CountDownLatch(1);

    public void writer() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = 1;          //1
        flag = true;   //2

        logger.info(a + " " + flag);
    }

    public void reader() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     /*  if(flag){      //3
           int i = a; //4
       }*/
        logger.info(a + " " + flag);
    }

    class A implements Runnable {

        @Override
        public void run() {
            VolatileExample.this.writer();
        }
    }

    class B implements Runnable {

        @Override
        public void run() {
            VolatileExample.this.reader();
        }
    }

    @Test
    public void fd() {
        VolatileExample exampl = new VolatileExample();
        A a = exampl.new A();
        B b = exampl.new B();
        (new Thread(a)).start();
        (new Thread(b)).start();
        exampl.latch.countDown();
    }

}

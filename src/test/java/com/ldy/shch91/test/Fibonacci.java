package com.ldy.shch91.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Fibonacci implements Generator<Integer> {

  private static Logger logger = LoggerFactory.getLogger(Fibonacci.class);

  private int count = 0;
  public Integer next() { return fib(count++); }
  private int fib(int n) {
    if(n < 2) return 1;
    return fib(n-2) + fib(n-1);
  }

  @Test
  public  void ghj() {
    Fibonacci gen = new Fibonacci();
    for(int i = 0; i < 18; i++)
      logger.info(gen.next() + "\t");
  }

  @Test
  public  void fda(){
      ReentrantReadWriteLock rw=new ReentrantReadWriteLock();

  }


}
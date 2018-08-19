package com.ldy.shch91.test;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Fibonacci implements Generator<Integer> {
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
      System.out.print(gen.next() + "\t");

    System.out.println();

    String s1 = "a";

    String s2 = s1 + "b";

    String s3 = "a" + "b";

    System.out.println(s2 == "ab");

    System.out.println(s3 == "ab");

  }

  @Test
  public  void fda(){
      ReentrantReadWriteLock rw=new ReentrantReadWriteLock();

  }


}
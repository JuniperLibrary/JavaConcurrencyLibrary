package org.dingchuan.lc;

import java.util.concurrent.CountDownLatch;

/**
 * @author dingchuan
 */
public class FooCountDownLatch {

  private final CountDownLatch firstCountDownLatch = new CountDownLatch(1);
  private final CountDownLatch secondCountDownLatch = new CountDownLatch(1);

  public FooCountDownLatch() {

  }

  public void first(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    // 计数器减1
    firstCountDownLatch.countDown();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    // 等待 firstCountDownLatch为0
    firstCountDownLatch.await();
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
    // 减1
    secondCountDownLatch.countDown();
  }

  public void third(Runnable printThird) throws InterruptedException {
    // 等待 secondCountDownLatch为0
    secondCountDownLatch.await();
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
  }
  public static void main(String[] args) {
    FooCountDownLatch foo = new FooCountDownLatch();
    new Thread(() -> {
      try {
        foo.first(() -> System.out.print("first"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"thread1").start();
    new Thread(() -> {
      try {
        foo.second(() -> System.out.print("second"));
      } catch (InterruptedException e) {
       e.printStackTrace();
     }
   },"thred2").start();
    new Thread(() -> {
      try {
        foo.third(() -> System.out.print("third"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"thread3").start();
 }
}

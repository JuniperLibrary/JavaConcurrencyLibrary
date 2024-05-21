package org.dingchuan.lc.lc1114;

import java.util.concurrent.Semaphore;

/**
 * @author dingchuan
 */
public class FooSemaphore {
  private final Semaphore firstSemaphore = new Semaphore(0);
  private final Semaphore secondSemaphore = new Semaphore(0);

  public FooSemaphore() {

  }

  public void first(Runnable printFirst) throws InterruptedException {

    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    // 释放信号量
    firstSemaphore.release();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    // acquire() 获取信号量，如果信号量不足，则阻塞等待
    firstSemaphore.acquire();
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
    // 释放信号量
    secondSemaphore.release();
  }

  public void third(Runnable printThird) throws InterruptedException {
    secondSemaphore.acquire();
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
  }

  public static void main(String[] args) {
    FooSemaphore foo = new FooSemaphore();
    new Thread(() -> {
      try {
        foo.first(() -> System.out.print("first"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        foo.second(() -> System.out.print("second"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        foo.third(() -> System.out.print("third"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}

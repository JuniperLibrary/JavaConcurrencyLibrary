package org.dingchuan.lc.lc1115;

import java.util.concurrent.CountDownLatch;

public class FooBarUsingCountDownLatch {

  private final int n;
  private final CountDownLatch startSignal;
  private final CountDownLatch doneSignal;

  public FooBarUsingCountDownLatch(int n) {
    this.n = n;
    this.startSignal = new CountDownLatch(1);
    // 两倍于n，因为每个线程各执行n次
    this.doneSignal = new CountDownLatch(n * 2);
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      // 等待开始信号
      startSignal.await();
      printFoo.run();
      // 完成一次操作，计数器减一
      doneSignal.countDown();
      // 再次等待开始信号
      startSignal.await();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      startSignal.await(); // 等待开始信号
      printBar.run();
      doneSignal.countDown(); // 完成一次操作，计数器减一
      startSignal.await(); // 再次等待开始信号
    }
  }

  public void start() {
    new Thread(() -> {
      for (int i = 0; i < n * 2; i++) { // 交替启动foo和bar
        if (i % 2 == 0) {
          startSignal.countDown(); // 发送开始信号
        }
        doneSignal.countDown(); // 确保两个线程都完成了一次操作后再次发送开始信号
      }
    }).start();
  }

  public static void main(String[] args) {
    FooBarUsingCountDownLatch fooBar = new FooBarUsingCountDownLatch(2);
    new Thread(() -> {
      try {
        fooBar.foo(() -> System.out.print("foo"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        fooBar.bar(() -> System.out.print("bar"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    fooBar.start(); // 启动控制线程
  }
}

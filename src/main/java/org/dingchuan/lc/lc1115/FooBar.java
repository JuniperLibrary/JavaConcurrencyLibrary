package org.dingchuan.lc.lc1115;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dingchuan
 */
//给你一个类：
//
//class FooBarUsingCyclicBarrier {
//  public void foo() {
//    for (int i = 0; i < n; i++) {
//      print("foo");
//    }
//  }
//
//  public void bar() {
//    for (int i = 0; i < n; i++) {
//      print("bar");
//    }
//  }
//}
//两个不同的线程将会共用一个 FooBarUsingCyclicBarrier 实例：
//
//线程 A 将会调用 foo() 方法，而
//线程 B 将会调用 bar() 方法
//请设计修改程序，以确保 "foobar" 被输出 n 次。
// 输入：n = 2
//输出："foobarfoobar"
//解释："foobar" 将被输出两次。
public class FooBar {


  private int n;
  private Semaphore fooSema = new Semaphore(1);
  private Semaphore barSema = new Semaphore(0);

  public FooBar(int n) {
    this.n = n;
  }

  public synchronized void foo(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      fooSema.acquire();//值为1的时候，能拿到，执行下面的操作
      printFoo.run();
      barSema.release();//释放许可给barSema这个信号量 barSema 的值+1
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      barSema.acquire();//值为1的时候，能拿到，执行下面的操作
      printBar.run();
      fooSema.release();//释放许可给fooSema这个信号量 fooSema 的值+1
    }
  }

  public static void main(String[] args) {
    FooBar fooBar = new FooBar(2);
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
  }
}

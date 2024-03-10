package org.uin.product;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanglufei
 * @title: LockProduct
 * @projectName interview
 * @description: TODO
 * @date 2021/9/18/2:39 下午
 */
public class LockProduct {

  public static void main(String[] args) {
    Data1 data = new Data1();
    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "A").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "B").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "C").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "D").start();
  }
}

class Data1 {//数字 资源类
  private int number = 0;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();

  //+1
  public void increment() throws InterruptedException {
    lock.lock();
    try {
      while (number != 0) {
        //等待
        condition.await();
      }
      number++;
      System.out.println(Thread.currentThread().getName() + "=>" + number);
      //通知其他线程我+完了
      condition.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  //-1
  public void decrement() throws InterruptedException {
    lock.lock();
    try {
      while (number == 0) {
        //等待
        condition.await();
      }
      number--;
      System.out.println(Thread.currentThread().getName() + "=>" + number);
      //通知其他线程我-完了
      condition.signalAll();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

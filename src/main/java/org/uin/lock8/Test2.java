package org.uin.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @title: Test2
 * @projectName interview
 * @description: TODO
 * @date 2021/9/20/7:50 下午
 */
public class Test2 {

  public static void main(String[] args) {
    Phone2 phone1 = new Phone2();
    Phone2 phone2 = new Phone2();
    new Thread(() -> {
      phone1.sendSms();
    }, "A").start();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      phone2.call();
    }, "B").start();
  }
}

class Phone2 {

  /*
      synchronized锁的是对象方法的调用者
      两个方法用的是同一个锁，谁先拿到谁执行
   */
  public synchronized void sendSms() {
    try {
      TimeUnit.SECONDS.sleep(4);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("发短信");
  }

  public synchronized void call() {
    System.out.println("打电话");
  }

  public void hello() {
    System.out.println("hello");
  }
}

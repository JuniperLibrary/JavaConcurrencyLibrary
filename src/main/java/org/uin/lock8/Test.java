package org.uin.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁 就是关于锁的8个问题 1.标准情况下，两个线程先打印 发短信还是先执行打电话  1发短信 2打电话 2. sendSms 延迟4秒 两个线程先打印 发短信还是打电话 1发短信 2打电话
 *
 * @author wanglufei
 * @title: Test
 * @projectName interview
 * @description: TODO
 * @date 2021/9/20/7:29 下午
 */
public class Test {

  public static void main(String[] args) {
    Phone phone = new Phone();
    new Thread(() -> {
      phone.sendSms();
    }, "A");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      phone.call();
    }, "B");
  }
}

class Phone {

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
}

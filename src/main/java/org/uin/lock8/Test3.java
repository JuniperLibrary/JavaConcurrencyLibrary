package org.uin.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 增加两个静态的同步方法，只有一个对象，先打印 还是先发短信
 *
 * @author wanglufei
 * @title: Test3
 * @projectName interview
 * @description: TODO
 * @date 2021/9/20/8:00 下午
 */
public class Test3 {

  public static void main(String[] args) {
    Phone3 phone = new Phone3();
    new Thread(() -> {
      phone.sendSms();
    }, "A").start();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      phone.call();
    }, "B").start();
  }
}

class Phone3 {

  //synchronized 锁的对象是方法的调用者！
  //static 静态方法就有了！锁的是class 模版
  //类一加载
  public static synchronized void sendSms() {
    try {
      TimeUnit.SECONDS.sleep(4);

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("message");
  }

  public static synchronized void call() {
    System.out.println("call");
  }
}

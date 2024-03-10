package org.uin.Thread;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/7/6:11 PM
 */
public class Thread2 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + i);
    }
  }

  public static void main(String[] args) {
    //设置线程名字
    Thread.currentThread().setName("main thread:");
    Thread thread = new Thread(new Thread2());
    thread.setName("子线程:");
    //开启线程
    thread.start();
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName() + i);
    }
  }
}

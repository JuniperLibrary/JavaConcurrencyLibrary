package org.basic.newThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendsForThread extends Thread {

  public void run() {
    // 继承Thread 重新run方法
    System.out.println("run!");
  }

  public static void main(String[] args) {
    //设置线程名字
    Thread.currentThread().setName("main thread");
    ExtendsForThread thread1 = new ExtendsForThread();
    thread1.setName("子线程:");
    //开启线程
    thread1.start();
    for (int i = 0; i < 5; i++) {
      log.info(Thread.currentThread().getName() + i);
    }

    Thread thread = new Thread(() -> {log.info("线程2");}, "线程2");
    thread.start();

    ExtendsForThread extendsForThread = new ExtendsForThread();
    // 需要注意的是，当创建完thread对象后该线程并没有被启动执行，直到调用了start方法后才真正启动了线程.

    // 其实调用start方法后线程并没有马上执行而是处于就绪状态，这个就绪状态是指该线程已经获取了除CPU资源外的其他资源，等待获取CPU资源后才会真正处于运行状态。
    // 一旦run方法执行完毕，该线程就处于终止状态。
    extendsForThread.start();
  }
}

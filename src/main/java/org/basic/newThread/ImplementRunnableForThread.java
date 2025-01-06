package org.basic.newThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImplementRunnableForThread implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      log.info(Thread.currentThread().getName() + i);
    }
  }

  public static void main(String[] args) {
    // 设置线程名字
    Thread.currentThread().setName("main thread:");
    Thread thread = new Thread(new ImplementRunnableForThread());
    thread.setName("子线程:");
    // 启动线程
    thread.start();
    for (int i = 0; i < 5; i++) {
      log.info(Thread.currentThread().getName() + i);
    }
  }
}

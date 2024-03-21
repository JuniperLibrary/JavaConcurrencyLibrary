package org.dingchuan;

import lombok.extern.slf4j.Slf4j;

/**
 * 运行结果错误
 *
 * @author dingchuan
 */
@Slf4j
public class WrongResult {

  volatile static int i;

  public static void main(String[] args) throws InterruptedException {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        for (int j = 0; j < 10000; j++) {
          i++;
        }
      }
    };
    Thread thread1 = new Thread(r);
    thread1.start();
    Thread thread2 = new Thread(r);
    thread2.start();
    // join 主线程等待子线程执行完毕后再继续执行
    thread1.join();
    thread2.join();
    log.info("线程：[{}],等到结果:[{}]", Thread.currentThread().getName(), i);
  }
}

package org.uin.ConcurrentUtils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/8:43 PM
 */
public class Test {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(5);//
    for (int i = 0; i < 10; i++) {
      int num = i;
      new Thread(() -> {
        try {
          semaphore.acquire();
          System.out.println("线程" + num + "执行任务");
          TimeUnit.SECONDS.sleep(3);
          semaphore.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}

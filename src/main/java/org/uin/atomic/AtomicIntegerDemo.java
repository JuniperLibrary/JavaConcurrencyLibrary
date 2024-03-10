package org.uin.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/4:59 PM
 */
public class AtomicIntegerDemo {

  private static AtomicInteger i = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
    Runnable r = () -> {
      for (int j = 0; j < 100000; j++) {
        i.getAndIncrement();
      }
      System.out.println("自增完成！");
    };
    new Thread(r).start();
    new Thread(r).start();
    TimeUnit.SECONDS.sleep(1);
    System.out.println(i.get());
  }
}

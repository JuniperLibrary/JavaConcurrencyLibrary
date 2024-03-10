package org.uin.ThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/12:11 PM
 */
public class ExceptionThreadPool {

  public static void main(String[] args) throws InterruptedException {
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,
        1,
        0,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>());

    poolExecutor.execute(() -> {
      System.out.println(Thread.currentThread().getName());
      throw new RuntimeException("我是异常！");
    });
    TimeUnit.SECONDS.sleep(1);
    poolExecutor.execute(() -> {
      System.out.println(Thread.currentThread().getName());
    });
    poolExecutor.shutdownNow();
  }
}

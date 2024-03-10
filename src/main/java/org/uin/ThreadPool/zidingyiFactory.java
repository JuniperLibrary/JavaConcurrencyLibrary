package org.uin.ThreadPool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/12:05 PM
 */
public class zidingyiFactory {

  public static void main(String[] args) {
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
        new SynchronousQueue<>(),
        new ThreadFactory() {
          int count = 0;

          @Override
          public Thread newThread(Runnable r) {
            return new Thread(r, "我的自定义线程-" + count++);
          }
        });

    for (int i = 0; i < 4; i++) {
      poolExecutor.execute(
          () -> System.out.println(Thread.currentThread().getName() + " 开始执行！"));
    }
    poolExecutor.shutdownNow();
  }
}

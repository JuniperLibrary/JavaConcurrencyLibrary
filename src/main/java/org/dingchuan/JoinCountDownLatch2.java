package org.dingchuan;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class JoinCountDownLatch2 {

  private static CountDownLatch countDownLatch = new CountDownLatch(2);

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit((Runnable) () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        countDownLatch.countDown();
      }
      log.info("child 线程1执行完毕");
    });

    executorService.submit(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        countDownLatch.countDown();
      }
      log.info("child 线程2执行完毕");
    });

    log.info("等待 child 线程结束");
    //等待子线程执行完毕 返回
    countDownLatch.await();
    log.info("child 线程执行完毕");
    executorService.shutdown();
  }
}

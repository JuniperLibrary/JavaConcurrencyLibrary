package org.dingchuan;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class JoinCountDownLatch {

  // 创建一个CountDownLatch实例
  private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

  public static void main(String[] args) throws InterruptedException {
    Thread threadOne = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // 重新设置中断状态，并记录异常到日志
        Thread.currentThread().interrupt();
        log.error("{} was interrupted", Thread.currentThread().getName(), e);
      } finally {
        countDownLatch.countDown();
      }
      log.info("{} = child threadOne over!", Thread.currentThread().getName());
    });

    Thread threadTwo = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // 重新设置中断状态，并记录异常到日志
        Thread.currentThread().interrupt();
        log.error("{} was interrupted", Thread.currentThread().getName(), e);
      } finally {
        countDownLatch.countDown();
      }
      log.info("{} = child threadTwo over!", Thread.currentThread().getName());
    });

    // 启动子线程
    threadOne.start();
    threadTwo.start();

    log.info("wait all child thread over!");

    // 等待子线程执行完毕 返回
    countDownLatch.await();
    log.info("all child thread over!");
  }
}

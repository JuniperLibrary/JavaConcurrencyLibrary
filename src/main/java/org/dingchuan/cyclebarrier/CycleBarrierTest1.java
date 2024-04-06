package org.dingchuan.cyclebarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * 从字面意思理解，CyclicBarrier是回环屏障的意思，它可以让一组线程全部达到一个状态后再全部同时执行。
 * <p>
 * 这里之所以叫作回环是因为当所有等待线程执行完毕，并重置CyclicBarrier的状态后它可以被重用。
 * <p>
 * 之所以叫作屏障是因为线程调用await方法后就会被阻塞，这个阻塞点就称为屏障点，等所有线程都调用了await方法后，线程们就会冲破屏障，继续向下运行。
 *
 * @author dingchuan
 */
@Slf4j
public class CycleBarrierTest1 {

  private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
    @Override
    public void run() {
      log.info("{},线程都到达屏障点，继续执行",Thread.currentThread().getName());
    }
  });

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    executorService.submit(() -> {
      try {
        log.info("{},线程到达屏障点",Thread.currentThread().getName());
        log.info("{},线程继续执行",Thread.currentThread().getName());
        cyclicBarrier.await();
        log.info("{},线程继续执行",Thread.currentThread().getName());
      }catch (Exception e){
        e.printStackTrace();
      }
    });

    executorService.submit(() -> {
      try {
        log.info("{},线程到达屏障点",Thread.currentThread().getName());
        log.info("{},线程继续执行",Thread.currentThread().getName());
        cyclicBarrier.await();
        log.info("{},线程继续执行",Thread.currentThread().getName());
      }catch (Exception e){
        e.printStackTrace();
      }
    });
    // 关闭线程池
    executorService.shutdown();
  }
}

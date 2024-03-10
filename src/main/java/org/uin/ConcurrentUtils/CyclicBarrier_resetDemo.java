package org.uin.ConcurrentUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/7:49 PM
 */
public class CyclicBarrier_resetDemo {

  public static void main(String[] args) throws InterruptedException {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    for (int i = 0; i < 3; i++) {
      new Thread(() -> {
        try {
          cyclicBarrier.await();

        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      }).start();
    }
    Thread.sleep(500);
    System.out.println("当前屏障前线程等待数" + cyclicBarrier.getNumberWaiting());
    cyclicBarrier.reset();
    System.out.println("重置后屏障线程等待数" + cyclicBarrier.getNumberWaiting());
  }
}

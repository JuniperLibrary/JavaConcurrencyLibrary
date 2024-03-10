package org.uin.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @title: SemaphoreDemo
 * @projectName interview
 * @description: TODO
 * @date 2021/9/22/6:55 下午
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    //线程数量；停车位！限流！
    Semaphore semaphore = new Semaphore(3);
    for (int i = 1; i <= 6; i++) {
      new Thread(() -> {
        try {
          semaphore.acquire(); //得到
          System.out.println(Thread.currentThread().getName() + "抢到车位");
          TimeUnit.SECONDS.sleep(2);
          System.out.println(Thread.currentThread().getName() + "离开车位");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          semaphore.release(); //release释放
        }
      }, String.valueOf(i)).start();
    }
  }
}

package org.uin.ThreadPool;

import java.util.concurrent.*;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/8:06 PM
 */
public class ThreadPoolDemo {

  public static void main(String[] args) throws InterruptedException {
    //ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();//单个线程的线程池
    //ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);//多个线程的线程池
    //ExecutorService cachedThreadPool = Executors.newCachedThreadPool();//带有缓存的线程池

    //手动创建自定义的线程池
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        2,
        4,
        3,
        TimeUnit.SECONDS,
        new SynchronousQueue<>(),
        (r, executor1) -> {   //比如这里我们也来实现一个就在当前线程执行的策略
          System.out.println("哎呀，线程池和等待队列都满了，你自己耗子尾汁吧");
          r.run();   //直接运行
        });
    for (int i = 0; i < 6; i++) {
      int num = i;
      threadPoolExecutor.execute(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + "开始执行(" + num);
          TimeUnit.SECONDS.sleep(1);
          System.out.println(Thread.currentThread().getName() + "已结束（" + num);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    TimeUnit.SECONDS.sleep(1);
    System.out.println("线程池中线程的数量" + threadPoolExecutor.getPoolSize());
    TimeUnit.SECONDS.sleep(5);//等待时间超过空闲时间
    System.out.println("线程池中的数量" + threadPoolExecutor.getPoolSize());

    //关闭线程池
    threadPoolExecutor.shutdownNow();//他会取消所有在等待队列中的任务以及试图继续执行的任务
    threadPoolExecutor.shutdown();//同样关闭线程池，但是会执行等待队列中的任务在关闭

  }
}

package org.uin.ThreadPool;

import java.util.concurrent.*;

/**
 * 线程池的创建 线程的三大方法 7大参数 4中拒绝策略
 */
public class Demo1 {

  public static void main(String[] args) {
    //-------线程池的三大方法-------
    //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //单个线程
    //ExecutorService threadPool = Executors.newFixedThreadPool(5); //创建一个固定大小的线程池
    //ExecutorService threadPool =Executors.newCachedThreadPool(); //可以伸缩的线程池 带有缓存的线程池

    //------自定义线程池！------工作用的最多的 ThreadPoolExecutor
    //获取CPU的最大核数
    //System.out.println(Runtime.getRuntime().availableProcessors());
    ExecutorService threadPool = new ThreadPoolExecutor(2,
        5,
        3,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(3),
        Executors.defaultThreadFactory(),
        //拒绝策略 银行满了，还有人进来，不处理这个人，抛出异常
        new ThreadPoolExecutor.DiscardPolicy());
    try {
      //使用线程池创建线程
      //最大承载 : queue+maX
      //超出最大承载数就会抛出异常  RejectedExecutionException
      for (int i = 1; i <= 9; i++) {
        threadPool.execute(() -> {
          System.out.println(Thread.currentThread().getName() + " ok");
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      //线程池用完要关闭
      threadPool.shutdown();
    }
  }
  /**
   public ThreadPoolExecutor(int corePoolSize,
   int maximumPoolSize,
   long keepAliveTime,
   TimeUnit unit,
   BlockingQueue<Runnable> workQueue,
   ThreadFactory threadFactory,
   RejectedExecutionHandler handler) {
   if (corePoolSize < 0 ||
   maximumPoolSize <= 0 ||
   maximumPoolSize < corePoolSize ||
   keepAliveTime < 0)
   throw new IllegalArgumentException();
   if (workQueue == null || threadFactory == null || handler == null)
   throw new NullPointerException();
   this.acc = System.getSecurityManager() == null ?
   null :
   AccessController.getContext();
   this.corePoolSize = corePoolSize;
   this.maximumPoolSize = maximumPoolSize;
   this.workQueue = workQueue;
   this.keepAliveTime = unit.toNanos(keepAliveTime);
   this.threadFactory = threadFactory;
   this.handler = handler;
   }
   */
}

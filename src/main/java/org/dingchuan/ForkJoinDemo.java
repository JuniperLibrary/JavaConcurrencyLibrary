package org.dingchuan;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class ForkJoinDemo {

  public static void main(String[] args) {
    int n = 20;
    // 为了追踪子线程名称，需要重写 ForkJoinWorkerThreadFactory 的方法
    final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
      final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(
          pool);
      worker.setName("my-thread" + worker.getPoolIndex());
      return worker;
    };
    // 创建分治任务线程池，可以追踪到线程名称
    ForkJoinPool forkJoinPool = new ForkJoinPool(4, factory, null, false);

    // 创建分治任务
    Fibonacci fibonacci = new Fibonacci(n);
    // 调用invoke方法启动分治任务
    Integer invoke = forkJoinPool.invoke(fibonacci);
    log.info("Fibonacci 结果为：{}", invoke);
  }

  /**
   * 定义拆分任务 写好拆分逻辑
   */
  @Slf4j
  static class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) {
      this.n = n;
    }

    @Override
    protected Integer compute() {
      // 和递归类似 定义递归的结束条件
      if (n <= 1) {
        return n;
      }
      log.info(Thread.currentThread().getName());
      Fibonacci f1 = new Fibonacci(n - 1);
      // 拆分成子任务
      f1.fork();
      Fibonacci f2 = new Fibonacci(n - 2);
      // f1.join 等待子任务执行结果
      return f2.compute() + f1.join();
    }
  }
}

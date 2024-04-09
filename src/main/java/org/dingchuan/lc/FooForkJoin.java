package org.dingchuan.lc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FooForkJoin {

  public static class SequentialExecution extends RecursiveTask<Void> {

    private final Runnable first;
    private final Runnable second;
    private final Runnable third;

    public SequentialExecution(Runnable first, Runnable second, Runnable third) {
      this.first = first;
      this.second = second;
      this.third = third;
    }

    @Override
    protected Void compute() {
      first.run();
      second.run();
      third.run();
      return null;
    }
  }

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    SequentialExecution task = new SequentialExecution(
        () -> System.out.println("first"),
        () -> System.out.println("second"),
        () -> System.out.println("third"));

    forkJoinPool.submit(task).join();
  }

}

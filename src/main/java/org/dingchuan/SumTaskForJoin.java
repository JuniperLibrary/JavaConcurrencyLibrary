package org.dingchuan;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumTaskForJoin extends RecursiveTask<Long> {

  private List<Long> numbers;
  private int from;
  private int to;

  public SumTaskForJoin(List<Long> numbers, int from, int to) {
    this.numbers = numbers;
    this.from = from;
    this.to = to;
  }

  @Override
  protected Long compute() {
    if (to - from < 6) {
      long total = 0;
      for (int i = from; i <= to; i++) {
        total += numbers.get(from);
      }
      return total;
    } else {
      int mid = (from + to) / 2;
      SumTaskForJoin taskLeft = new SumTaskForJoin(numbers, from, mid);
      SumTaskForJoin taskright = new SumTaskForJoin(numbers, mid + 1, to);
      taskLeft.fork();
      taskright.fork();
      return taskright.join() + taskright.join();
    }
  }
}

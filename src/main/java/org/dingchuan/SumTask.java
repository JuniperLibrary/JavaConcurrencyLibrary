package org.dingchuan;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author dingchuan
 */
public class SumTask implements Callable<Long> {

  private List<Long> numbers;
  private int from;
  private int to;

  public SumTask(List<Long> numbers, int from, int to) {
    this.numbers = numbers;
    this.from = from;
    this.to = to;
  }

  @Override
  public Long call() {
    long total = 0;
    for (int i = from; i <= to; i++) {
      total += numbers.get(from);
    }
    return total;
  }
}

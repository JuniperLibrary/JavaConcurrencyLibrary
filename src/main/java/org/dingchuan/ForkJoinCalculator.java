package org.dingchuan;

import cn.hutool.core.date.StopWatch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class ForkJoinCalculator implements Calculator {

  private ForkJoinPool pool;

  public ForkJoinCalculator() {
    // 也可以使用公用的线程池
    // pool = ForkJoinPool.commonPool()
    pool = new ForkJoinPool();
  }

  @Override
  public long sumUp(List<Long> numbers) {
    try {
      return pool.invoke(new SumTaskForJoin(numbers, 0, numbers.size() - 1));
    } catch (Exception e) {

    } finally {
      pool.shutdown();
    }
    return 0;
  }

  public static void main(String[] args) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    ForkJoinCalculator forkJoinCalculator = new ForkJoinCalculator();
    ArrayList<Long> nums = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      nums.add((long) i);
    }
    log.info("计算的结果为：{}", forkJoinCalculator.sumUp(nums));
    stopWatch.stop();
    log.info("执行时长：{}", stopWatch.getLastTaskTimeMillis());
  }
}

package org.dingchuan;

import cn.hutool.core.date.StopWatch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class ExecutorServiceCalculator implements Calculator {

  private int parallelism;
  private ExecutorService pool;

  public ExecutorServiceCalculator() {
    // CPU的核心数 默认就用cpu核心数
    parallelism = Runtime.getRuntime().availableProcessors();
    pool = Executors.newFixedThreadPool(parallelism);
  }

  @Override
  public long sumUp(List<Long> numbers) {
    List<Future<Long>> results = new ArrayList<>();

    int part = numbers.size() / parallelism;

    for (int i = 0; i < parallelism; i++) {
      int from = i * part;
      int to = (i == parallelism - 1) ? numbers.size() - 1 : (i + 1) * part - 1;

      results.add(pool.submit(new SumTask(numbers, from, to)));
    }

    long total = 0;
    for (Future<Long> result : results) {
      try {
        total += result.get();
      } catch (Exception e) {
        log.error("获取结果出现异常!", e);
      }
    }
    return total;
  }


  public static void main(String[] args) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    ExecutorServiceCalculator executorServiceCalculator = new ExecutorServiceCalculator();
    ArrayList<Long> nums = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      nums.add((long) i);
    }
    log.info("结果为：{}", executorServiceCalculator.sumUp(nums));
    stopWatch.stop();
    log.info("执行时长：{}", stopWatch.getLastTaskTimeMillis());
  }
}

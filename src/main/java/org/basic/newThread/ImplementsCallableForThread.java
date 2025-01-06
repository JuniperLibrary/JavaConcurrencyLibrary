package org.basic.newThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImplementsCallableForThread implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    return sum;
  }


  public static void main(String[] args) {
    // 创建异步任务
    FutureTask<Integer> futureTask = new FutureTask<Integer>(new ImplementsCallableForThread());
    // 启动线程
    new Thread(futureTask).start();
    try {
      //接收线程运算后的结果
      Integer sum = futureTask.get();
      log.info("callable future result:", sum);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}

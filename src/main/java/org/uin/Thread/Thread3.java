package org.uin.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/7/6:27 PM
 */
public class Thread3 implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    return sum;
  }


  public static void main(String[] args) {
    FutureTask<Integer> futureTask = new FutureTask<Integer>(new Thread3());
    new Thread(futureTask).start();
    //接收线程运算后的结果
    try {
      Integer sum = futureTask.get();
      System.out.println(sum);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}

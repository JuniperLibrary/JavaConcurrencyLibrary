package org.uin.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/2:25 PM
 */
public class ScheduledThreadPoolExecutorDemo {

  public static void main(String[] args) {
    //直接设定核心线程数为1
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    //这里我们计划在3秒后执行
    scheduledExecutorService.schedule(() -> System.out.println("Hello World!"), 3,
        TimeUnit.SECONDS);
    scheduledExecutorService.shutdown();
  }
}

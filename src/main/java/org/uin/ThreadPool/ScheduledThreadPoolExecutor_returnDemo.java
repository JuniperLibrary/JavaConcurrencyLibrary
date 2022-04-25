package org.uin.ThreadPool;

import java.util.concurrent.*;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/2:29 PM
 */
public class ScheduledThreadPoolExecutor_returnDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleAtFixedRate(() -> System.out.println("Hello World!"),
                3, 1, TimeUnit.SECONDS);

        ScheduledFuture<String> future = executor.schedule(() -> "1231231", 4, TimeUnit.SECONDS);

        System.out.println("任务剩余等待时间："+future.getDelay(TimeUnit.MILLISECONDS) / 1000.0 + "s");
        System.out.println("任务执行结果："+future.get());
        executor.shutdown();
    }
}

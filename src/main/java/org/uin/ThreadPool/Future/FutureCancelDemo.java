package org.uin.ThreadPool.Future;

import java.util.concurrent.*;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/2:05 PM
 */
public class FutureCancelDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "这次一定！";
        });
        System.out.println(future.cancel(true));
        System.out.println(future.isCancelled());
        executorService.shutdown();
    }
}

package org.uin.ThreadPool.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/1:59 PM
 */
public class FutureStatusDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> "我是返回结果");
        System.out.println(submit.get());
        System.out.println("任务是否执行"+submit.isDone());
        System.out.println("任务是否被取消"+submit.isCancelled());
        executorService.shutdownNow();
    }
}

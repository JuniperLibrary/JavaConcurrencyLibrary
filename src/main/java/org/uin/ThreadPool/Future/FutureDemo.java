package org.uin.ThreadPool.Future;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/1:22 PM
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //Future<String> submit = executorService.submit(() -> "我是返回结果");
        //Future<String> submit = executorService.submit(() -> {
            //try {
                //TimeUnit.SECONDS.sleep(3);
            //} catch (InterruptedException e) {
                //e.printStackTrace();
            //}
        //}, "我是返回结果");
//        Future<?> submit = executorService.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(submit.get());
        FutureTask<String> task = new FutureTask<>(() -> "我是返回结果");
        executorService.submit(task);
        System.out.println(task.get());
        executorService.shutdownNow();
    }
}

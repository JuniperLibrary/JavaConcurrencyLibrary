package org.uin.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/1:36 PM
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println("线程开始");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("线程结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

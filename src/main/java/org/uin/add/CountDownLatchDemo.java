package org.uin.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author wanglufei
 * @title: CountDownLatchDemo
 * @projectName interview
 * @description: TODO
 * @date 2021/9/22/7:05 下午
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助
        //总数是6，必须要执行任务的时候，在使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go out");
                countDownLatch.countDown();// 数量-1
            }, String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归零，然后在向下执行
        System.out.println("Close Door");
        /*
        countDownLatch.countDown(); 数量-1
        countDownLatch.await() 等待计数器归零，然后在向下执行
         */
    }
}

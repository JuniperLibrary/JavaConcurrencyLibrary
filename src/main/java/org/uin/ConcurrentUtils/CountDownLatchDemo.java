package org.uin.ConcurrentUtils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/4:28 PM
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //有20个计算任务，我们需要先将这些任务的结果全部计算出来，每个任务的执行时间未知
        //当所有任务结束之后，立即整合统计最终结果
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            int num = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (2000 * new Random().nextDouble()));
                    System.out.println("子任务" + num + "执行完成！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//每执行一次计数器都会-1
            }).start();
        }
        //开始等待所有的线程完成，当计数器为0时，恢复运行
        countDownLatch.await();   //这个操作可以同时被多个线程执行，一起等待，这里只演示了一个
        System.out.println("所有子任务都完成！任务完成！！！");

        //注意这个计数器只能使用一次，用完只能重新创一个，没有重置的说法
    }
}

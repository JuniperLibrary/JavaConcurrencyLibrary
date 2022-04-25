package org.uin.ConcurrentUtils;

import java.util.concurrent.Semaphore;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/8:41 PM
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);   //只配置一个许可证，5个线程进行争抢，不内卷还想要许可证？
        for (int i = 0; i < 5; i++)
            new Thread(semaphore::acquireUninterruptibly).start();   //可以以不响应中断（主要是能简写一行，方便）
        Thread.sleep(500);
        System.out.println("剩余许可证数量："+semaphore.availablePermits());
        System.out.println("是否存在线程等待许可证："+(semaphore.hasQueuedThreads() ? "是" : "否"));
        System.out.println("等待许可证线程数量："+semaphore.getQueueLength());
    }

}

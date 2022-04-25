package org.uin.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/5:06 PM
 */
public class AtomicLongVSLongAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("使用AtomicLong的时间消耗：" + test2() + "ms");
        System.out.println("使用LongAdder的时间消耗：" + test1() + "ms");
    }

    private static long test1() throws InterruptedException {
        //CountDownLatch计数器
        CountDownLatch latch = new CountDownLatch(100);
        LongAdder adder = new LongAdder();
        long timeStart = System.currentTimeMillis();
        Runnable r = () -> {
            for (int i = 0; i < 100000; i++)
                adder.add(1);
            latch.countDown();
        };
        for (int i = 0; i < 100; i++)
            new Thread(r).start();
        latch.await();
        return System.currentTimeMillis() - timeStart;
    }

    private static long test2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(100);
        AtomicLong atomicLong = new AtomicLong();
        long timeStart = System.currentTimeMillis();
        Runnable r = () -> {
            for (int i = 0; i < 100000; i++)
                atomicLong.incrementAndGet();
            latch.countDown();
        };
        for (int i = 0; i < 100; i++)
            new Thread(r).start();
        latch.await();
        return System.currentTimeMillis() - timeStart;
    }
}

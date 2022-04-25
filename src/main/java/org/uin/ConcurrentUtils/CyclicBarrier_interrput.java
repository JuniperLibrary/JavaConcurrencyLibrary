package org.uin.ConcurrentUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/8:02 PM
 */
public class CyclicBarrier_interrput {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        Runnable r = () -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
        t.interrupt();
        new Thread(r).start();
    }
}

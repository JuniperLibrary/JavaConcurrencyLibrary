package org.uin.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wanglufei
 * @title: CyclicBarrier
 * @projectName interview
 * @description: TODO
 * @date 2021/9/22/7:06 下午
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐7课龙珠召唤神龙
         */
        //召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            //lambda能操作到i吗
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "个龙珠");
                try {
                    cyclicBarrier.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(temp)).start();
        }
    }
}

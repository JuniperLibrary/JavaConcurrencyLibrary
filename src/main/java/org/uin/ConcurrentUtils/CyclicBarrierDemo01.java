package org.uin.ConcurrentUtils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/7:43 PM
 */
public class CyclicBarrierDemo01 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5);  //创建一个初始值为10的计数器锁

        for (int i = 0; i < 10; i++) {   //创建5个线程
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep((long) (2000 * new Random().nextDouble()));
                    System.out.println("玩家 " + finalI + " 进入房间进行等待... (" + barrier.getNumberWaiting() + "/5)");

                    barrier.await();    //调用await方法进行等待，直到等待线程到达5才会一起继续执行

                    //人数到齐之后，可以开始游戏了
                    System.out.println("玩家 " + finalI + " 进入游戏！");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

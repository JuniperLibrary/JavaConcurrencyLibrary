package org.uin.concurrent;

import java.util.concurrent.DelayQueue;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/5:24 PM
 */
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Test> delayQueue = new DelayQueue<>();
        delayQueue.add(new Test(1,2,"2号"));
        delayQueue.add(new Test(3,1,"1号"));
        System.out.println(delayQueue.take());
        System.out.println(delayQueue.take());
    }
}

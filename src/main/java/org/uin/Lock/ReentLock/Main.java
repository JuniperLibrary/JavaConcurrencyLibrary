package org.uin.Lock.ReentLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/17/10:58 AM
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
//        lock.lock();
//        lock.lock();
//        System.out.println("当前加锁几次" + lock.getHoldCount() + "," + "是否被锁" + lock.isLocked());
//        new Thread(() -> {
//            System.out.println("线程2想要获取锁");
//            lock.lock();
//            System.out.println("线程2获取锁成功");
//        }).start();
//        lock.unlock();
//        System.out.println("线程1释放锁");
//        TimeUnit.SECONDS.sleep(1);
//        lock.unlock();
//        System.out.println("线程1再次释放锁");
        Runnable action = () -> {
            System.out.println("线程"+Thread.currentThread().getName()+"开始获取锁。。。");
            lock.lock();
            System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁。。。。");
            lock.unlock();
        };
        for (int i = 0; i < 10; i++) {
            new Thread(action,"T"+i).start();
        }
    }
}

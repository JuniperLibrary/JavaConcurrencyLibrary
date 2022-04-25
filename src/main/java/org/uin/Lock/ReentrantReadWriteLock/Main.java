package org.uin.Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/17/12:56 PM
 */
public class Main {
    public static void main(String[] args) {
//        读-读 可以共存
//        读-写 不可以共存
//        写-写 不可以共存
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
        //多个线程对读锁 加读锁 可以
        //reentrantReadWriteLock.readLock().lock();
        //new Thread(reentrantReadWriteLock.readLock()::lock).start();
        //System.out.println("ok");
        //多个线程同时对写锁 加写锁 可以
        //reentrantReadWriteLock.writeLock().lock();
        //new Thread(reentrantReadWriteLock.writeLock()::lock).start();
        //System.out.println("可以");

        //有读锁的情况下 是不能加写锁的 不可以
        reentrantReadWriteLock.readLock().lock();
        new Thread(reentrantReadWriteLock.writeLock()::lock).start();

        //有写锁的情况下 是不能加读锁的 不可以
        //reentrantReadWriteLock.writeLock().lock();
        //new Thread(reentrantReadWriteLock.readLock()::lock).start();

    }
}

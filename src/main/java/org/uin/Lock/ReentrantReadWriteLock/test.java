package org.uin.Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/17/1:14 PM
 */
public class test {

  public static void main(String[] args) throws InterruptedException {
    //默认是不公平的
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    lock.writeLock().lock();
    lock.writeLock().lock();
//        new Thread(() -> {
//            lock.writeLock().lock();
//            System.out.println("成功获取到写锁");
//        }).start();
//        System.out.println("释放第一层锁");
//        lock.writeLock().unlock();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("释放第二层锁");
//        lock.writeLock().unlock();

    //公平的案例
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
//        Runnable action = () -> {
//            System.out.println("线程 " + Thread.currentThread().getName() + " 将在1秒后开始获取锁...");
//            lock.writeLock().lock();
//            System.out.println("线程 " + Thread.currentThread().getName() + " 成功获取锁！");
//            lock.writeLock().unlock();
//        };
//        for (int i = 0; i < 10; i++) {   //建立10个线程
//            new Thread(action, "T" + i).start();
//        }
  }
}

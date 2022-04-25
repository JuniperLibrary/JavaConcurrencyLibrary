package org.uin.demo2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁
 *
 * @author wanglufei
 * @title: SaleTicketsDemo2
 * @projectName interview
 * @description: TODO
 * @date 2021/9/15/5:07 下午
 */
public class SaleTicketsDemo2 {
    public static void main(String[] args) {
        //并发：多线程操作同一个资源，把资源丢入线程
        Ticket2 ticket2 = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i <= 30; i++) {
                ticket2.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 30; i++) {
                ticket2.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 30; i++) {
                ticket2.sale();
            }
        }, "C ").start();
    }
}

//资源类 OOP

/**
 * Lock三部曲
 * 1. new ReentrantLock();
 * 2. lock.lock();
 * 3. lock.unlock();
 */
class Ticket2 {
    private Integer number = 30;
    //使用Lock锁
    Lock lock = new ReentrantLock(); //可重入锁

    public void sale() {
        lock.lock();
        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "数,剩余" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }

    }
}

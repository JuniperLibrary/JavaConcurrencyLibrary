package org.uin.lock8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/7/5:36 PM
 */
public class ThreadTest {
    private int j;
    //true 为公平锁 默认是非公平锁 也就是false
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        for (int i = 0; i < 2; i++) {
            new Thread(test.new Adder()).start();
            new Thread(test.new Subtractor()).start();
        }
    }

    private class Adder implements Runnable {

        @Override
        public void run() {

            while (true) {
//                synchronized (ThreadTest.this) {
//                    System.out.println("j++=" + j++);
//                }

                lock.lock();
                try {
                    System.out.println("j++=" + j++);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Subtractor implements Runnable {
        @Override
        public void run() {
            while (true){
//                synchronized (ThreadTest.this){
//                    System.out.println("j--="+j--);
//                }

                lock.lock();
                try {
                    System.out.println("j-- ="+ j--);
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}

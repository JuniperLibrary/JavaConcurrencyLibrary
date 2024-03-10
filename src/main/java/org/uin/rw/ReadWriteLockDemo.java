package org.uin.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wanglufei
 * @title: ReadWriteLockDemo
 * @projectName interview
 * @description: TODO
 * @date 2021/9/22/8:09 下午
 */
public class ReadWriteLockDemo {

  /*
  独占锁（写锁）一次只能被被一个线程锁占有
  共享锁（读锁）多个线程可以同时占有
  ReadWriteLock
      lock
      unlock
      读-读 可以共存
      读-写 不可以共存
      写-写 不可以共存
   */
  public static void main(String[] args) {
    MyCacheLOck myCache = new MyCacheLOck();
    for (int i = 1; i <= 5; i++) {
      final int temp = i;
      new Thread(() -> {
        myCache.put(temp + "", temp + "");
      }, String.valueOf(i)).start();
    }

    for (int i = 1; i <= 5; i++) {
      final int temp = i;
      new Thread(() -> {
        myCache.get(temp + "");
      }, String.valueOf(i)).start();
    }
  }
}

/*
 自定义缓存
*/
//class MyCache {
//    private volatile Map<String, Object> map = new HashMap<>();
//
//    // 存，写
//    public void put(String key, Object value) {
//        System.out.println(Thread.currentThread().getName() + "写入" + key);
//        map.put(key, value);
//        System.out.println(Thread.currentThread().getName() + "写入OK");
//    }
//
//    //取，读
//    public void get(String key) {
//        System.out.println(Thread.currentThread().getName() + "读取" + key);
//        Object o = map.get(key);
//        System.out.println(Thread.currentThread().getName() + "读取OK");
//    }
//}
//加锁的缓存
class MyCacheLOck {

  private volatile Map<String, Object> map = new HashMap<>();
  //读写锁，更加细粒度的控制
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  // 存，写
  //写入的时候，只希望只有一个线程往里写
  public void put(String key, Object value) {
    readWriteLock.writeLock();//加入写锁
//        readWriteLock.writeLock().lock();
//        Lock lock = new ReentrantLock();
//        try {
//            lock.lock();
//            System.out.println(Thread.currentThread().getName() + "写入" + key);
//            map.put(key, value);
//            System.out.println(Thread.currentThread().getName() + "写入OK");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
    System.out.println(Thread.currentThread().getName() + "写入" + key);
    map.put(key, value);
    System.out.println(Thread.currentThread().getName() + "写入OK");
  }

  //取，读
  //读的时候所有人都可以读
  public void get(String key) {
    readWriteLock.readLock();//加入读锁
    System.out.println(Thread.currentThread().getName() + "读取" + key);
    Object o = map.get(key);
    System.out.println(Thread.currentThread().getName() + "读取OK");
  }
}

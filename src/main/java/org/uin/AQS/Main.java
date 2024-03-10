package org.uin.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/17/4:46 PM
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();
//        Condition condition = lock.newCondition();
//        condition.await();
//        MyLock myLock = new MyLock();
//        Condition condition = myLock.newCondition();
//        myLock.lock();
//        new Thread(() -> {
////            System.out.println("线程2等待获取锁");
////            myLock.lock();
////            System.out.println("线程2拿到了锁");
//            try {
//                System.out.println("线程2开始获取锁");
//                TimeUnit.SECONDS.sleep(1);
//                myLock.lock();
//                System.out.println("线程2开始唤醒线程1");
//                condition.signal();
//                myLock.unlock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        System.out.println("线程1开始等待");
//        condition.await();
////        System.out.println("线程1开始释放锁");
//        System.out.println("线程1结束等待");
//        myLock.unlock();

    MyLock myLock = new MyLock();
    myLock.lock();
    new Thread(() -> {
      System.out.println("线程2等待获取锁");
      myLock.lock();
      System.out.println("线程2拿到了锁");
    }).start();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("线程1开始释放锁");
    myLock.unlock();
  }

  public static class MyLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

      @Override
      protected boolean tryAcquire(int arg) {
        if (isHeldExclusively()) {
          return true;     //无需可重入功能，如果是当前线程直接返回true
        }
        if (compareAndSetState(0, arg)) {    //CAS操作进行状态替换
          setExclusiveOwnerThread(Thread.currentThread());    //成功后设置当前的所有者线程
          return true;
        }
        return false;
      }

      @Override
      protected boolean tryRelease(int arg) {
        if (getState() == 0) {
          throw new IllegalMonitorStateException();   //没加锁情况下是不能直接解锁的
        }
        if (isHeldExclusively()) {     //只有持有锁的线程才能解锁
          setExclusiveOwnerThread(null);    //设置所有者线程为null
          setState(0);    //状态变为0
          return true;
        }
        return false;
      }

      @Override
      protected boolean isHeldExclusively() {
        return getExclusiveOwnerThread() == Thread.currentThread();
      }

      protected Condition newCondition() {
        return new ConditionObject();    //直接用现成的
      }

    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
      sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
      sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
      return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
      return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
      sync.release(1);
    }

    @Override
    public Condition newCondition() {
      return sync.newCondition();
    }
  }
}

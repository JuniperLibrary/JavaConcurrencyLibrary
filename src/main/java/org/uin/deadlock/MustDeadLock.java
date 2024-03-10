package org.uin.deadlock;

/**
 * 描述：必定死锁的情况
 *
 * @author wanglufei
 * @date 2022/7/30 11:53 PM
 */
public class MustDeadLock implements Runnable {

  public int flag;
  static Object o1 = new Object();
  static Object o2 = new Object();

  @Override
  public void run() {
    System.out.println("线程" + Thread.currentThread().getName() + "的flag为" + flag);
    if (flag == 1) {
      synchronized (o1) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
          e.printStackTrace();
        }
        synchronized (o2) {
          System.out.println("线程1获得了两把锁");
        }
      }
    }
    if (flag == 2) {
      synchronized (o2) {
        try {
          Thread.sleep(500);
        } catch (Exception e) {
          e.printStackTrace();
        }
        synchronized (o1) {
          System.out.println("线程2获得了锁");
        }
      }
    }
  }

  public static void main(String[] args) {
    MustDeadLock deadLock1 = new MustDeadLock();
    MustDeadLock deadLock2 = new MustDeadLock();
    deadLock1.flag = 1;
    deadLock2.flag = 2;

    Thread t1 = new Thread(deadLock1, "t1");
    Thread t2 = new Thread(deadLock2, "t2");

    t1.start();
    t2.start();
  }
}

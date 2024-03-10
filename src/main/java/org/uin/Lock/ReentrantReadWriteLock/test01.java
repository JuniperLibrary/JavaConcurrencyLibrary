package org.uin.Lock.ReentrantReadWriteLock;

/**
 * @author wanglufei
 * @description: 主要做的测试 主线程先加写锁 在后加读锁 其他线程来尝试获取这个读锁
 * @date 2022/3/17/1:45 PM
 */
public class test01 {

  public static void main(String[] args) throws InterruptedException {
    try {
      return;
    } finally {
      System.out.println("Finally");
    }
  }

}

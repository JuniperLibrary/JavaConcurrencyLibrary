package org.dingchuan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dingchuan
 */
public class MyBlockingQueueForCondition {

  private Queue queue;
  private int max = 16;
  private ReentrantLock lock = new ReentrantLock();
  private Condition notEmpty = lock.newCondition();
  private Condition notFull = lock.newCondition();

  public MyBlockingQueueForCondition(int size) {
    this.max = size;
    queue = new LinkedList();
  }

  public void put(Object o) throws InterruptedException {
    lock.lock();
    try {
      // 如果队列满了 生产者阻塞
      while (queue.size() == max) {
        notFull.await();
      }
      queue.add(o);
      // 唤醒消费者来消费
      notEmpty.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public Object take() throws InterruptedException {
    lock.lock();
    try {
      // 如果队列是空的 消费者阻塞
      while (queue.size() == 0) {
        notEmpty.await();
      }
      Object item = queue.remove();
      // 唤醒生产者 生产
      notFull.signalAll();
      return item;
    } finally {
      lock.unlock();
    }
  }
}

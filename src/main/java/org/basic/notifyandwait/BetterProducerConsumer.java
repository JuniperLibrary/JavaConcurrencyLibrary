package org.basic.notifyandwait;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BetterProducerConsumer {

  private final Queue<Integer> queue = new LinkedList<>();
  private final int CAPACITY = 5;
  private final Object LOCK = new Object();
  private volatile boolean isRunning = true;

  public void produce() throws InterruptedException {
    int value = 0;
    while (isRunning) {
      synchronized (LOCK) {
        try {
          while (queue.size() == CAPACITY && isRunning) {
            LOCK.wait();
          }

          if (!isRunning) {
            break;
          }

          queue.offer(value);
          log.info("生产: {}" , value++);
          LOCK.notifyAll();

        } catch (InterruptedException e) {
          isRunning = false;
          throw e;
        }
      }
    }
  }

  public void shutdown() {
    isRunning = false;
    synchronized (LOCK) {
      LOCK.notifyAll(); // 唤醒所有等待的线程
    }
  }

}

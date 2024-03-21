package org.dingchuan;

import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dingchuan
 */
@Slf4j
public class MyBlockingQueue {
  private int maxSize = 16;
  private LinkedList<Object> storage;

  public MyBlockingQueue(int size) {
    this.maxSize = size;
    storage = new LinkedList<>();
  }

  public synchronized void put() throws InterruptedException {
    while (storage.size() == maxSize) {
      wait();
    }
    storage.add(new Object());
    notifyAll();
  }

  public synchronized void take() throws InterruptedException {
    while (storage.isEmpty()) {
      wait();
    }
    log.info("take [{}]",storage.remove());
    notifyAll();
  }
}

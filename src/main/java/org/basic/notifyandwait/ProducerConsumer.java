package org.basic.notifyandwait;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerConsumer {

  private final Queue<Integer> queue = new LinkedList<>();

  private final int CAPACITY = 5;

  private final Object LOCK = new Object();

  public void produce() throws InterruptedException {
    int value = 0;
    while (true) {
      synchronized (LOCK) {
        // 队列满了，生产者需要等待
        while (queue.size() == CAPACITY) {
          log.info("队列满了，生产者等待...");
          LOCK.wait();  // 释放锁，等待消费者消费
        }

        // 生产数据
        queue.offer(value);
        log.info("生产: {}" ,value);
        value++;

        // 通知消费者可以消费了
        LOCK.notifyAll();

        Thread.sleep(1000); // 模拟生产过程
      }
    }
  }

  public void consume() throws InterruptedException {
    while (true) {
      synchronized (LOCK) {
        // 队列空了，消费者需要等待
        while (queue.isEmpty()) {
          log.info("队列空了，消费者等待...");
          // 阻塞挂起
          LOCK.wait();  // 释放锁，等待生产者生产
        }

        // 消费数据
        int value = queue.poll();
        log.info("消费: {}", value);

        // 通知生产者可以生产了
        LOCK.notifyAll();

        Thread.sleep(1000); // 模拟消费过程
      }
    }
  }

  public static void main(String[] args) {
    ProducerConsumer example = new ProducerConsumer();

    // 创建生产者线程
    Thread producerThread = new Thread(() -> {
      try {
        example.produce();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 创建消费者线程
    Thread consumerThread = new Thread(() -> {
      try {
        example.consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    producerThread.start();
    consumerThread.start();
  }
}

package org.dingchuan;

import java.util.ArrayDeque;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 多线程生产者消费模式
 *
 * @author dingchuan
 */
@Slf4j
public class ProducerConsumerDemo {

  public static void main(String[] args) {
    Buffer buffer = new Buffer();
    Consumer consumer = new Consumer(buffer);
    Producer producer = new Producer(buffer);
    producer.start();
    consumer.start();
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  static class Producer extends Thread {

    private Buffer buffer;

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          buffer.add(i);
          log.info("生产者生产:{}", i);
          Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  static class Consumer extends Thread {

    private Buffer buffer;

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          int i1 = buffer.get();
          log.info("消费者消费：{}", i1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  static class Buffer {

    private Queue<Integer> queue = new ArrayDeque<>();

    private int size = 2;

    public void add(int i) throws InterruptedException {
      synchronized (this) {
        // 阻塞生产者 等待消费者消费
        if (queue.size() >= size) {
          log.info("消息数量达到：{},超过上限：{},等待消费者进行消费！", queue.size(), size);
          wait();
        }
        queue.add(i);
        // 唤醒消费者
        notify();
      }
    }

    public int get() throws InterruptedException {
      synchronized (this) {
        if (queue.isEmpty()) {
          wait();
        }
        Integer poll = queue.poll();
        notify();
        return poll;
      }
    }

  }
}

package org.quant;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于批量处理，当接收的数据量达到一定数量或者达到某个时间间隔时触发事件
 * <p>
 * 用例：行情入库，接收到100条数据或者等了1秒还没有拿到新的数据则触发批量入库
 */
@Slf4j
@Accessors(chain = true)
public class QitIntervalQueue<T> {

  private BlockingQueue<T> queue;

  private Consumer<List<T>> consumer;

  @Setter
  private int batchSize = 100;

  // 等待最大时间，默认1秒
  @Setter
  private long interval = 1000;

  @Setter
  private int limitQueueSize = 5000;

  // 计时器
  private final TimeInterval timeInterval = new TimeInterval();

  public void put(T data) {
    try {
      // 如果没有接收的方法则把数据丢掉
      if (consumer != null && data != null) {
        queue.put(data);
      }
    } catch (Exception e) {
      log.error("put failed", e);
    }
  }

  public QitIntervalQueue<T> setConsumer(Consumer<List<T>> consumer) {
    Assert.notNull(consumer, "consumer cannot be null");
    Assert.isNull(this.consumer, "consumer has already been set yet");
    this.consumer = consumer;
    queue = new ArrayBlockingQueue<>(limitQueueSize);
    QitThreadFactory.startThread("interval-queue-" + consumer.toString(), () -> {
      List<T> list = new ArrayList<>();
      while (true) {
        try {
          T data = queue.poll(interval, TimeUnit.MILLISECONDS);
          // data == null 是因为为等待超时
          if (data != null) {
            list.add(data);
          }
          if (list.size() >= batchSize || timeInterval.interval() > interval) {
            consume(list);
          }
        } catch (Exception e) {
          log.error(e.getMessage(), e);
        }
      }
    });
    return this;
  }

  private void consume(List<T> list) {
    if (CollUtil.isNotEmpty(list)) {
      try {
        consumer.accept(list);
      } catch (Exception e) {
        log.error("consume data failed", e);
      } finally {
        timeInterval.restart();
        // 为了防止异常导致的列表无限增大
        list.clear();
      }
    }
  }
  public int getQueueSize(){
    return queue.size();
  }
}

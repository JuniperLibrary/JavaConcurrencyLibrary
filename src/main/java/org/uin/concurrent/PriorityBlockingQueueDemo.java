package org.uin.concurrent;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/5:18 PM
 */
public class PriorityBlockingQueueDemo {

  public static void main(String[] args) {
    //默认11
    //可以指定初始容量（可扩容）和优先级比较规则，这里我们使用升序
    PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>(10,
        Integer::compare);
    priorityBlockingQueue.add(3);
    priorityBlockingQueue.add(2);
    priorityBlockingQueue.add(1);
    //注意保存顺序并不会按照优先级排列，所以可以看到结果并不是排序后的结果
    System.out.println(priorityBlockingQueue);
    //但是出队顺序一定是按照优先级进行的
    System.out.println(priorityBlockingQueue.poll());
    System.out.println(priorityBlockingQueue.poll());
    System.out.println(priorityBlockingQueue.poll());
  }
}

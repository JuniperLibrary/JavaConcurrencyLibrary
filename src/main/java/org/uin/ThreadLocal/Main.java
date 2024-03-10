package org.uin.ThreadLocal;

import java.util.stream.IntStream;

public class Main {
  //因为ThreadLocal里面设置的值，只有当前线程可见，也就以为着你不可能通过其他线程为它初始化值
  //为了弥补这一点，ThreadLocal提供了一个withInitial()方法统一初始化所有线程的ThreadLocal的值：
  //ThreadLocal的初始值设置为6，这对全体线程都是可见的
  //private static ThreadLocal<Integer> localInt = ThreadLocal.withInitial(() -> 6);

  public static void main(String[] args) {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    IntStream.range(0, 10).forEach(i -> new Thread(() -> {
      threadLocal.set(Thread.currentThread().getName() + ":" + i);
      System.out.println(
          "线程：" + Thread.currentThread().getName() + ",local:" + threadLocal.get());
    }).start());

  }
}

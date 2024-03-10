package org.uin.concurrent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/1:33 PM
 */
public class ConcurrentHashMapDemo {

  public static void main(String[] args) throws InterruptedException {
//        HashMap
    ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
    for (int i = 0; i < 100; i++) {
      int num = i;
      new Thread(() -> {
        for (int j = 0; j < 100; j++) {
          concurrentHashMap.put(num * 100 + j, "bearbrick0");
        }
      }).start();
    }
    TimeUnit.SECONDS.sleep(1);
    System.out.println(concurrentHashMap.size());
  }
}

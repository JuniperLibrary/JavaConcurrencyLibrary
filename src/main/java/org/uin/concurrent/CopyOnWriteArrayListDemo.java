package org.uin.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/1:08 PM
 */
public class CopyOnWriteArrayListDemo {

  public static void main(String[] args) {
    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    Runnable r = () -> {
      for (int i = 0; i < 100; i++) {
        list.add("berabrick0");
      }
    };
    for (int i = 0; i < 100; i++) {
      new Thread(r).start();
    }
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println(list.size());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

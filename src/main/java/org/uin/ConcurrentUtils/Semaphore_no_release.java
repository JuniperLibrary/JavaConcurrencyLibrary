package org.uin.ConcurrentUtils;

import java.util.concurrent.Semaphore;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/8:37 PM
 */
public class Semaphore_no_release {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(3);
    for (int i = 0; i < 2; i++) {
      new Thread(() -> {
        try {
          semaphore.acquire();
          System.out.println("许可证申请成功！");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}

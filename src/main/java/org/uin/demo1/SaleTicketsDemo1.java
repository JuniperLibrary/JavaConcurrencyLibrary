package org.uin.demo1;

/**
 * @author wanglufei
 * @title: SaleTicketsDemo1
 * @projectName JUC
 * @description: TODO
 * @date 2021/9/511:10 下午
 */
public class SaleTicketsDemo1 {

  /**
   * 真正的多线程开发，公司中的开发，会降低耦合性 线程就是一个单独的资源类，没有任何的附属操作 1. 属性、方法
   *
   * @param args
   */
  public static void main(String[] args) {
    //new  Thread(new Tickets()).start();
    //多线程操作
    //并发就是：多线程操作一个资源类
    //@FunctionalInterface 一个函数式接口 jdk1.8 lambda表达式(参数)->{代码}
    final Tickets tickets = new Tickets();
    new Thread(() -> {
      for (int i = 0; i <= 30; i++) {
        tickets.sale();
      }
    }, "A").start();
    new Thread(() -> {
      for (int i = 0; i <= 30; i++) {
        tickets.sale();
      }
    }, "B").start();
    new Thread(() -> {
      for (int i = 0; i <= 30; i++) {
        tickets.sale();
      }
    }, "C ").start();

  }

}

//资源类 oop（不要实现实现类，会增加程序的耦合性）
//class Tickets implements Runnable{
//
//    public void run() {
//
//    }
//}
//资源类 oop编程
class Tickets {

  //属性、方法
  private int number = 30;

  //卖票的方法
  //synchronized本质上是队列、锁
  public synchronized void sale() {
    if (number > 0) {
      System.out.println(
          Thread.currentThread().getName() + "卖出了" + (number--) + "数,剩余" + number);
    }
  }
}

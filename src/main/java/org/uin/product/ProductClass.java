package org.uin.product;

/**
 * 线程之前通信的问题：生产者和消费者问题 等待唤醒，通知唤醒 线程之间交替执行 A B 之间操作同一个变量 num=0 A  num+1 B  num-1
 *
 * @author wanglufei
 * @title: ProductClass
 * @projectName interview
 * @description: TODO
 * @date 2021/9/15/7:07 下午
 */
public class ProductClass {

  /**
   * 有问题 当4个线程一起跑的时候，就会出现3和4 造成虚假唤醒的问题 线程也可以被唤醒，而不会被通知，中断或超时，即所谓的虚假唤醒。虽然在这实践中很少会发生，但应用程序必须通过测试应该
   * 是线程使被唤醒的条件和规范，并且如果条件不满足继续等待。换句话说，等待应该总是出现在循环中，就像这样 在官方文档中，要求使用while循环来做判断
   *
   * @param args
   */
  public static void main(String[] args) {
    Data data = new Data();
    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "A").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.decrement();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "B").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "C").start();

    new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        try {
          data.increment();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "D").start();
  }
}

//判断等待唤醒，执行业务，通知唤醒
class Data {//数字 资源类
  private int number = 0;

  //+1
  public synchronized void increment() throws InterruptedException {
    while (number != 0) {
      //等待
      this.wait();
    }
    number++;
    System.out.println(Thread.currentThread().getName() + "=>" + number);
    //通知其他线程我+完了
    this.notifyAll();
  }

  //-1
  public synchronized void decrement() throws InterruptedException {
    while (number == 0) {
      //等待
      this.wait();
    }
    number--;
    System.out.println(Thread.currentThread().getName() + "=>" + number);
    //通知其他线程我-完了
    this.notifyAll();
  }
}

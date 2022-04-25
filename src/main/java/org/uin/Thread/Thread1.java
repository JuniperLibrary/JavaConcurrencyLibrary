package org.uin.Thread;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/7/6:07 PM
 */
public class Thread1 extends Thread{
    public void run(){
        System.out.println("run!");
    }

    public static void main(String[] args) {
        //设置线程名字
        Thread.currentThread().setName("main thread");
        Thread1 thread1 = new Thread1();
        thread1.setName("子线程:");
        //开启线程
        thread1.start();
        for(int i = 0;i<5;i++){
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

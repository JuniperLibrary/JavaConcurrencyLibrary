package org.uin;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/15/8:15 PM
 */
public class Main {
    //public static void main(String[] args) {
    //for (int i = 0; i < 100; i++) {
    //synchronized (Main.class) {
    //很明显这样加锁的操作很消耗性能
    //}
    //}
    //synchronized (Main.class){

    //}
    //for (int i = 0; i < 100; i++) {

    //}

    //}
    //相当于主内存 成员变量 静态变量都存在jvm内存模型的堆中（堆是线程共享的）
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int j = 0; j < 100000; j++) i++;
            System.out.println("线程1结束");
        }).start();
        new Thread(() -> {
            for (int j = 0; j < 100000; j++) i++;
            System.out.println("线程2结束");
        }).start();
        //等上面两个线程结束
        Thread.sleep(1000);
        System.out.println(i);

        //输出结果：
        //线程1结束
        //线程2结束
        //173382
    }
}

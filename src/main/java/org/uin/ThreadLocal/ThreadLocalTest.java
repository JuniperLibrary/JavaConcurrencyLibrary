package org.uin.ThreadLocal;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/2:41 PM
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> local = new ThreadLocal<String>();

    static void print(String str) {
        //打印当前线程中本地内存中变量的值
        System.out.println(str + " :" + local.get());
        //清除内存中的本地变量
        local.remove();
    }
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                ThreadLocalTest.local.set("BearBrick0");
                print("A");
                //打印本地变量
                System.out.println("清除后：" + local.get());
            }
        },"A").start();
        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                ThreadLocalTest.local.set("BearBrick0");
                print("B");
                System.out.println("清除后 " + local.get());
            }
        },"B").start();
    }
}





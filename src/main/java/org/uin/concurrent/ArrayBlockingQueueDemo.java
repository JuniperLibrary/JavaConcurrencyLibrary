package org.uin.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/2:50 PM
 */
public class ArrayBlockingQueueDemo {
    //通过多线程编程，来模拟一个餐厅的2个厨师和3个顾客
    //假设厨师炒出一个菜的时间为3秒，顾客吃掉菜品的时间为4秒
    //窗口上只能放一个菜。
    public static void main(String[] args) {
        //阻塞队列来做这个窗口 指定只能放一个菜
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<Object>(1);
        //厨师
        Runnable chushi = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.err.println(time() + "厨师" + name + "正在做饭。。。。。");
                    TimeUnit.SECONDS.sleep(3);
                    System.err.println(time() + "厨师" + name + "做好了");
                    blockingQueue.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };
        //顾客
        Runnable consumer = () -> {
            while (true) {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println(time() + "消费者 " + name + " 正在等待出餐...");
                    blockingQueue.take();
                    System.out.println(time() + "消费者 " + name + " 取到了餐品。");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(time() + "消费者 " + name + " 已经将饭菜吃完了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //两个厨师做
        for (int i = 0; i < 2; i++) {
            new Thread(chushi, "厨师" + i).start();
        }
        //三个顾客吃
        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "顾客" + i).start();
        }
    }

    public static String time() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return "[" + dateFormat.format(new Date()) + "]";
    }
}

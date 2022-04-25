package org.uin.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @title: BlockingQueue
 * @projectName interview
 * @description: TODO
 * @date 2021/9/22/8:56 下午
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        /*
        Collection
            List
                ArrayList
                LinkedList
            Set
            Queue
                BlockingQueue 阻塞队列
                    ArrayBlockingQueue
                    LinkedBlockingQueue
                QueueDeque 双端队列
                AbstractQueue 非阻塞队列
         */
        test4();
    }

    /*
     * 抛出异常
     */
    public static void test1() {
        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.add("d"));//IllegalStateException
        System.out.println(blockingQueue.element());//查看对首的元素

        System.out.println("======================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

//        System.out.println(blockingQueue.remove());//NoSuchElementException

    }

    /*
        不抛出异常，给返回值
     */
    public static void test2() {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d")); //不抛出异常，返回false
        System.out.println(blockingQueue.peek());//查看对首元素
        System.out.println("=================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//返回null
    }

    /*
        阻塞等待（一直等待）
     */
    public static void test3() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        //一直阻塞，没有返回值
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        blockingQueue.put("d");//队列没有位置，就一直阻塞

        System.out.println("============");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
    }

    /*
        阻塞等待（等待超时）
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);//等待超过2秒就退出
        System.out.println("==========");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));//等待2秒就退出
    }
}

package org.uin.concurrent;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/5:05 PM
 */
public class LinkedTransferQueueDemo {
    public static void main(String[] args) {
        LinkedTransferQueue<Object> transferQueue = new LinkedTransferQueue<>();
        //插入时，会先检查是否有其他线程等待获取，如果是，直接进行交接，否则插入到存储队列中
        transferQueue.put("bearbrick0");
        //不会像SynchronousQueue那样必须等一个匹配的才可以
        transferQueue.put("uin");
        //直接打印所有的元素，这在SynchronousQueue下只能是空，因为单独的入队或出队操作都会被阻塞
        transferQueue.forEach(System.out::println);
    }
}

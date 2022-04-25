package org.uin.concurrent;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/5:08 PM
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.put("uin");
        //阻塞
        blockingQueue.put("bearbrick0");
        new Thread(() -> {
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

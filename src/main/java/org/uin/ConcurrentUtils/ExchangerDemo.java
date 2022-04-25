package org.uin.ConcurrentUtils;

import java.util.concurrent.Exchanger;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/21/8:53 PM
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                System.out.println("收到主线程传递的交换数据："+exchanger.exchange("BearBrick0"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("收到子线程传递的交换数据："+exchanger.exchange("uin"));
    }
}

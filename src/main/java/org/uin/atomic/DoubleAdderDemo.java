package org.uin.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/5:05 PM
 */
public class DoubleAdderDemo {
    public static void main(String[] args) throws InterruptedException {
        DoubleAdder adder = new DoubleAdder();
        Runnable r = () -> {
            for (int i = 0; i < 100000; i++)
                adder.add(1);
        };
        for (int i = 0; i < 100; i++)
            new Thread(r).start();   //100个线程
        TimeUnit.SECONDS.sleep(1);
        System.out.println(adder.sum());   //最后求和即可
    }
}

package org.uin.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/5:01 PM
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5, 6};
        AtomicIntegerArray integerArray = new AtomicIntegerArray(array);
        Runnable r = () -> {
            for (int i = 0; i < 100000; i++)
                integerArray.getAndAdd(0, 1);
        };
        new Thread(r).start();
        new Thread(r).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(integerArray.get(0));

    }
}

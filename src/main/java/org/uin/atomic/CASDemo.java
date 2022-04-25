package org.uin.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/5:00 PM
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(10);
        //AtomicInteger integer = new AtomicInteger();
        //懒加载
        //integer.lazySet(10);
        System.out.println(integer.compareAndSet(30, 20));
        System.out.println(integer.compareAndSet(10, 20));
        System.out.println(integer);
    }
}

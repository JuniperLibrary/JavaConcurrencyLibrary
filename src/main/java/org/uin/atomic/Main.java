package org.uin.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/18/1:54 PM
 */
public class Main {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(1);
        System.out.println(integer.incrementAndGet());
    }
}

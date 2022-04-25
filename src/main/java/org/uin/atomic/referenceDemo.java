package org.uin.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/4:56 PM
 */
public class referenceDemo {
    public static void main(String[] args) {
        String a = "Hello";
        String b = "World";
        AtomicReference<String> reference = new AtomicReference<>(a);
        reference.compareAndSet(a, b);
        System.out.println(reference.get());
    }
}

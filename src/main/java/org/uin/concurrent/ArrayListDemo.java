package org.uin.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/19/6:51 PM
 */
public class ArrayListDemo {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                list.add("bearbrick0");
            }
        };
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list.size());
    }
}

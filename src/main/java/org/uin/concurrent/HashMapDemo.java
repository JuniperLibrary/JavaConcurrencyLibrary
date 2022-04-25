package org.uin.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglufei
 * @description: TODO
 * @date 2022/3/20/12:24 PM
 */
public class HashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int num = i;
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    objectObjectHashMap.put(num * 1000 + j,"bearbrick0" );
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(objectObjectHashMap.size());
    }
}

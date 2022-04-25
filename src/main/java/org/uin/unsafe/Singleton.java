package org.uin.unsafe;

/**
 * @author wanglufei
 * @description: 双重检验锁
 * @date 2022/2/17/3:06 PM
 */
public class Singleton {
    public volatile static Singleton singleton;

    public Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                       singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

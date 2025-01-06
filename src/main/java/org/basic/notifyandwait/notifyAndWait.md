当一个线程调用一个共享变量的wait（）方法时，该调用线程会被阻塞挂起，直到发生下面几件事情之一才返回：

1. 其他线程调用了该共享对象的notify（）或者notifyAll（）方法；
2. 其他线程调用了该线程的interrupt（）方法，该线程抛出InterruptedException异常返回。另外需要注意的是，如果调用wait（）方法的线程没有事先获取该对象的监视器锁，则调用wait（）方法时调用线程会抛出IllegalMonitorStateException异常。



在 Java 中，获取共享变量的监视器锁主要有以下几种方式：
使用 synchronized 关键字
```java
public class SynchronizedExample {
    private Object shareObject = new Object(); // 共享对象
    
    // 方法一：synchronized 修饰方法
    public synchronized void method1() {
        // 整个方法被锁住
        System.out.println("获得了当前对象的锁");
    }
    
    // 方法二：synchronized 代码块，锁定指定对象
    public void method2() {
        synchronized(shareObject) {
            // 只有获得shareObject的锁的线程才能执行这块代码
            System.out.println("获得了shareObject的锁");
        }
    }
}
```
使用 Lock 接口的实现类
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private Lock lock = new ReentrantLock(); // 可重入锁
    
    public void method() {
        lock.lock(); // 获取锁
        try {
            // 临界区代码
            System.out.println("获得了锁");
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}
```
几个重要的注意点：
1. synchronized 的特点：
- 自动获取和释放锁
- 可重入性
- 发生异常时自动释放锁
- 不能中断一个正在等待获取锁的线程
2. Lock 接口的特点： 
- 需要手动获取和释放锁
- 可以尝试获取锁（tryLock()）
- 可以设置获取锁的超时时间
- 可以中断获取锁的过程
- 提供更好的性能和更大的灵活性
3. 最佳实践：
- 优先使用 synchronized，代码简单，不容易出错
- 需要更灵活的锁机制时，使用 Lock
- 使用 Lock 时，一定要在 finally 块中释放锁
- 尽量缩小同步范围，避免长时间持有锁
示例：等待超时的锁获取
```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeoutLockExample {
    private Lock lock = new ReentrantLock();
    
    public void method() {
        try {
            // 尝试获取锁，最多等待5秒
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    // 获得锁后的操作
                    System.out.println("成功获得锁");
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("获取锁超时");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```
这些是在 Java 中获取共享变量监视器锁的主要方式。选择哪种方式取决于你的具体需求：
- 如果是简单的同步需求，使用 synchronized 即可
- 如果需要更复杂的功能（如超时、中断等），则考虑使用 Lock 接口的实现类
记住，无论使用哪种方式，都要注意避免死锁，并确保正确释放锁。

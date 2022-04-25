# 面试问题：**单列模式、排序算法、生产者和消费者问题、死锁**

# 什么是JUC

Java.util.concurrent 并存的，是关于并发编程的API。

# 并发编程

## 进程和线程

继承Thread 实现Runnable接口 实现callable接口

1. 线程有几个状态？
    1. NEW 新生
    2. RUNNABLE 运行
    3. BLOCKED 阻塞
    4. WAITING 等待，死死的等待
    5. TIME_WAITING 超时等待，就不等了
    6. TERMINATED 终止
2. wait和sleep的区别
    1. 来自不同的类
        1. wait来自Object类
        2. sleep来自Thread类
        3. 企业当中使用休眠 juc下的TimeUnit
    2. 关于锁的释放
        1. wait会释放锁
        2. sleep会抱着锁睡觉，不会释放
    3. 使用范围是不同的
        1. wait必须是在同步代码块中使用
        2. sleep可以在任何地方睡觉，前提是有人让你等
    4. 是否需要捕获异常
        1. wait不需要捕获异常
        2. sleep必须要捕获异常
3. lock锁
    1. 传统锁synchronized
        1. 锁方法
            1. 静态方法
            2. 实例方法
        2. 同步代码块
            1. 作用范围
                1. 实例对象
                2. class对象
                3. 任意实例的Object对象
            2. 执行同步代码块后首先要先执行monitorenter指令，退出的时候monitorexit指令。通过分析之后可以看出，
               使用Synchronized进行同步，其关键就是必须要对对象的监视器monitor进行获取，当线程获取monitor
               后才能继续往下执行，否则就只能等待。而这个获取的过程是互斥的，即同一时刻只有一个线程能够获取到monitor。
    2. JUC-->下的lock锁
        1. ReentrantLock 可重入锁（常用）
            1. 公平锁：十分公平，先来后到 fairySync
            2. 非公平锁：十分不公平，可以插队 NonfairySync（默认）
    3. Lock锁和synchronized有什么区别
        1. synchronized是Java内置的一个关键字，Lock是是一个Java类
        2. synchronized无法判断获取锁的状态，而lock锁可以判断是否获取到了锁
        3. synchronized回自动释放锁，而lock必须手动释放锁。如果不释放就会变成死锁
        4. synchronized 线程1（获得锁，阻塞）线程2（傻傻地等待）lock就不一定会等待
        5. synchronized 可重入锁，不可以中断的，非公平。lock锁 可重入锁，可以判断锁，公平不公平自己可以设置
        6. synchronized适合锁少量的代码同步问题 Lock适合锁大量的同步代码
    4. 锁是什么？如何判断锁的是谁？
        1.
4. 生产者和消费者问题
    1. 生产者和消费者问题synchronized版
        1. synchronized
            1. wait
            2. notify
        2. 当线程过多的时候，防止虚假唤醒（线程可以被唤醒，而不被通知，中断或超时），业务代码要使用while来做判断
    2. 生产者和消费者问题JUC版 Lock版
        1. lock
            1. condition _替代了Synchronized监视的效果，取代了对象监视器的的方法_
            2. condition 精准的通知和唤醒线程
                1. 条件（也称为条件队列或条件变量）为一个线程暂停执行（等待）
                    1. await
                    2. signal
5. 8锁现象
    1. 如何判断锁的是谁！永远地知道什么锁，锁的到底是谁
        1. new this 具体的一个手机
        2. static Class 锁的是class对象 唯一的一个模版
        3. synchronized 锁的是方法的调用者
6. 集合类不安全 ConcurrentModificationException
    1. ArrayList 不安全
    2. Vector 线程安全 Synchronized
    3. Arrays工具类
    4. CopyOnWriteArrayList transient volatile 使用 **final ReentrantLock lock = this.lock**
        1. 写入时复制 COW 计算机程序设计领域的一种优化策略
        2. 多个线程调用的时候，list，读取的时候，固定的，写入（覆盖）
        3. 在写入的时候避免覆盖，造成数据问题
        4. 读写分离 MyCat 数据库层面
    5. 为什么不用Vector而用CopyOnWriteArrayList
        1. 因为只要有Synchronized，他的效率偏低
7. set不安全 ConcurrentModificationException
    1. Set<String> set = Collections.synchronizedSet(new HashSet<>()) Collections工具类
    2. Set<String> set = new CopyOnWriteArraySet<>()
8. HashSet的底层是什么？
    1. HashMap
    2. add set 本质就是map key 是无法重复的
9. HashMap ConcurrentModificationException
    1. initialCapacity 16初时容量 loadFactor 加载因子 0.75 默认值
    2. 工作不是这样用HashMap的 Map<String,String> map = new HashMap<>();
    3. 默认等价于什么？ new HashMap<>(16,0.75);
    4. 并发下的HashMap
        1. Map<String,String> map = new ConcurrentHashMap<>();
        2. 底层
            1. 支持检索的完全并发性和更新的高预期并发性的哈希表。这个类从相同的功能规范如HashSet
10. 常用的辅助类
    1. CountDownLatch
    2. CyclicBarrier
    3. Semaphore
        1. 一个计数信号量，在概念上，信号量维持一组许可证。如果有必要，每个acquire()都会阻塞，直达许可证可用，然后才能 使用它。每个release()
           添加许可证，潜在地释放阻塞获取方。但是，没有使用实际的许可证对象；Semaphore只保留可用数量的计数，并相应地执行 信号量通常用于限制线程数，而不是访问某些资源。例如，这是一个使用信号量来控制对一个项目池的访问的类
           在获得项目之前，每个线程必须从信号量获取许可证，以确保某个项目可用。当线程完成该项目的时候，他将返回到池中，并将许可证返回到信号量，允许另一个线程获取该项目
    4. 原理
        1. semaphore.acquire() 获取，假设如果已经满了，等待，等待被释放为止
        2. semaphore.release() 释放，会将当前的信号量释放，+1，然后唤醒等待的线程！
        3. 作用：多个共享资源互斥的使用！并发限流，控制最大的线程数！
11. 读写锁 ReadWriteLock
    1. 一个用于只读操作，一个用于写入操作。写的时候是独占的，读的时候可以由多个线程同时进行读
    2. 所有的ReadWriteLock实现必须保证的存储器同步效应writeLock操作（如指定Lock接口）也保持相对于所属相关联的readLock 。也就是说，一个线程成功获取读锁定将会看到之前发布的写锁定所做的所有更新。
    3. 读写锁允许访问共享数据时的并发性高于互斥锁所允许的并发性。他利用了这样一个事实：一次只有一个线程（写入线程）可以修改共享数据，在许多的情况下，
       任何数量的线程都可同时的读取数据（读取器线程）。从理论上讲，通过使用读写锁允许的并发性增加将会导致性能改进超过使用互斥锁。实际上，并发性的增加只能在多处理器上完全实现，然后只有在共享数据的访问模式是合适时才可以。
    4. 独占锁（写锁） 一次只能被一个线程占有
    5. 共享锁（读锁） 多个线程可以同时占有
    6. 也叫共享锁和排它锁（互斥锁）
12. 阻塞队列 BlockingQueue
    1. 阻塞（类似于生产者和消费者问题）
        1. 称为不得不阻塞
            1. 写入的时候，如果当队列满的时候会出现阻塞，就必须等待消费
            2. 取的时候，如果队列为空的时候，就必须阻塞，就必须等待生产
    2. 队列
        1. FIFO 先进先出
    3. 在juc中BlockingQueue 阻塞队列
    4. 什么情下我们会使用阻塞队列
        1. 多线程并发处理 线程池
    5. 添加、移除、判断队列的首部
        1. 四组API
            1. 抛出异常
            2. 不回抛出异常，有返回值
            3. 阻塞等待
            4. 超时等待
    6. SynchronousQueue 同步队列
        1. 没有容量 进去一个元素，必须等待取出来之后，才能再往里面放一个元素！
        2. put take
13. 线程池 **三大方法、七大参数、4中拒绝策略**
    1. 程序的运行，本质：占用系统的资源！优化资源的使用！=》池化技术
    2. 线程池、连接池、内存池、对象池
    3. 池化技术：事先准备好一些资源，有人要用，就来我这里，用完之后还给我
    4. 线程池的好处
        1. 降低资源消耗：线程池通常会维护一些线程（数量为 corePoolSize），这些线程被重复使用来执行不同的任务，任务完成后不会销毁。
           在待处理任务量很大的时候，通过对线程资源的复用，避免了线程的频繁创建与销毁，从而降低了系统资源消耗。
        2. 提高响应速度：由于线程池维护了一批 alive 状态的线程，当任务到达时，不需要再创建线程，而是直接由这些线程去执行任务，从而减少了任务的等待时间。
        3. 提高线程的可管理性：使用线程池可以对线程进行统一的分配，调优和监控。
           **线程可以复用、可以控制最大连接数、管理线程**
    5. 线程池常问：
        1. 三大方法、七大参数、4中拒绝策略
    6. 线程的三大方法
        1. 在阿里巴巴的考法手册中强制规定线程池的创建不允许使用Executors去创建线程，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学 更加明确线程的运行规则，规避资源耗尽的风险
           说明：Executors返回的线程对象的弊端如下：
            1. FixedThreadPool 和 SingleThreadPool
                1. 允许的请求的长度为Integer.MAX_VALUE,可能会堆积大量的请求，从而导致OOM
            2. CacheThreadPool和 ScheduledThreadPool
                1. 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM
        2. **Executors.newSingleThreadExecutor(); //单个线程 Executors.newFixedThreadPool(5); //创建一个固定大小的线程池
           Executors.newCachedThreadPool(); //可以伸缩的线程池**
    7. 线程池的七大参数
        1. int corePoolSize 核心线程大小
        2. int maximumPoolSize 最大核心线程池大小
        3. long keepAliveTime 超时了 没有人连接就会释放
        4. TimeUnit unit 超时单位
        5. BlockingQueue<Runnable> workQueue 阻塞队列
        6. ThreadFactory 线程工厂
        7. RejectedExecutionHandler handler 拒绝策略
    8. 四种拒绝策略
        1. AbortPolicy 超出最大承载数就会抛出异常 RejectedExecutionException
        2. CallerRunsPolicy 那来的去哪里 直接运行这个任务的run方法，但并非是由线程池的线程处理，而是交由任务的调用线程处理
        3. DiscardOldestPolicy 将当前处于等待队列列头的等待任务强行取出，然后再试图将当前被拒绝的任务提交到线程池执行 队列满了，尝试去和最早地去竞争 也不会抛出异常
        4. DiscardPolicy 直接丢弃任务，不抛出任何异常 队列满了，不会抛出异常
    9. 小结和拓展
       1. 怎么样设置最大线程池数 池的最大的大小如何设置
          1. cpu密集型 CPU是几核，就设置几核 可以保持CPU的效率最高
             1. System.out.println(Runtime.getRuntime().availableProcessors()); 8 核
          2. iO密集型  判断你的程序中十分耗费IO资源的线程
             1. 比如 有15个大型任务 IO十分占有资源 设置大于2倍
14. 四大函数式接口（必须掌握）
    1. **新时代的程序员：lambda表达式、函数式编程（链式编程）、函数式接口、Stream流式计算**
    2. 
## 并发和并行

1. 并发（多线程操作同一个资源） CPU 一核 ，模拟出来多条线程，天下武功，为快不破，快速交替
2. 并行（多个人一起行走） CPU多核，多个线程可以同时执行；线程池

并发编程的本质：充分利用cpu的资源 

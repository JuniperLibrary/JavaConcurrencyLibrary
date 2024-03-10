package org.uin.AQS.CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wanglufei
 * @description: CAS操作会带来ABA问题
 * @date 2022/3/19/5:19 PM
 */
public class ABADemo {

  public static void main(String[] args) {
    String a = "Hello";
    String b = "World";
    //在构造时需要指定初始值和对应的版本号 就是盖了个戳
    AtomicStampedReference<String> reference = new AtomicStampedReference<>(a, 1);
    //可以中途对版本号进行修改，注意要填写当前的引用对象
    reference.attemptStamp(a, 2);
    //CAS操作时不仅需要提供预期值和修改值，还要提供预期版本号和新的版本号
    System.out.println(reference.compareAndSet(a, b, 2, 3));
  }
}

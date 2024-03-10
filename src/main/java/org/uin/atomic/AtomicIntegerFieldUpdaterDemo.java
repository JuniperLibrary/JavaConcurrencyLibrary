package org.uin.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author wanglufei
 * @description: 原子更新器 原子性的操作
 * @date 2022/3/19/4:57 PM
 */
public class AtomicIntegerFieldUpdaterDemo {

  public static void main(String[] args) {
    Student student = new Student();
    AtomicIntegerFieldUpdater<Student> fieldUpdater =
        AtomicIntegerFieldUpdater.newUpdater(Student.class, "age");
    System.out.println(fieldUpdater.incrementAndGet(student));
  }

  public static class Student {

    volatile int age;
  }
}

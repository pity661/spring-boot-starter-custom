package com.wenky.starter.custom.basic.load;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-11 16:41
 */
public class NewInstance {
  public static void main(String[] args) {
    // 0
    System.out.println(A.a); // 1

    // A static
    // 0
    // System.out.println(A.b); // 2

    // A static
    // A.c = 1; // 3

    // A static
    // B.c = 1; // 4

    // A static
    // A a
    // B.a(); // 5
  }

  public static class A {
    public static final int a = 0;
    public static final Integer b = 0;
    public static Integer c;

    // 仅在类初始化时执行一次
    static {
      System.out.println("A static");
    }

    public static void a() {
      System.out.println("A a");
      return;
    }
  }

  public static class B extends A {

    // 仅在类初始化时执行一次
    static {
      System.out.println("B static");
    }
  }
}

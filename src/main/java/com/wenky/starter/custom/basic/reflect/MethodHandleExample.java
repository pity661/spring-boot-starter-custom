package com.wenky.starter.custom.basic.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-22 11:15
 */
public class MethodHandleExample {

  static class GrandFather {
    void thinking() {
      System.out.println("I 'm grandFather!");
    }
  }

  static class Father extends GrandFather {
    void thinking() {
      System.out.println("I 'm father!");
    }
  }

  static class Son extends Father {
    void thinking() {
      // 实现祖父类的thinking(),打印 I 'm grandFather
      MethodType methodType = MethodType.methodType(void.class);
      try {
        // 指定GrandFather类，但是调用的还是Father中的方法
        MethodHandle methodHandle =
            MethodHandles.lookup()
                .findSpecial(GrandFather.class, "thinking", methodType, this.getClass());
        methodHandle.invoke(this);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    MethodHandleExample.Son son = new Son();
    // I 'm father!
    son.thinking();
  }
}

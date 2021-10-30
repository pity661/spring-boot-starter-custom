package com.wenky.starter.custom.thread.basic;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-06 14:08
 */
public class IsInterruptedExample {
  public static void main(String[] args) throws InterruptedException {
    isInterruptedTest();
  }

  private static void isInterruptedTest() throws InterruptedException {
    Runnable runnable =
        () -> {
          System.out.println("start");

          // 执行循环知道线程被置于中断位
          while (!Thread.currentThread().isInterrupted()) {
            Thread.yield();
            // System.out.println(Thread.currentThread().getName());
          }

          // true
          System.out.println(Thread.currentThread().isInterrupted());
          // 先将线程置于中断位，然后执行sleep
          // 线程抛出异常后，中断位被清除
          try {
            Thread.sleep(100L);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          // false
          System.out.println(Thread.currentThread().isInterrupted());
          System.out.println("end");
        };

    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(100);
    thread.interrupt();
  }
}

package com.wenky.starter.custom.thread.basic;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-05 11:07
 */
public class InterruptExample {
  public static void main(String[] args) throws InterruptedException {
    interruptTest();
  }

  // isInterrupted 和 interrupted 的区别，都是获取线程中断状态的方法
  // interrupted 调用后会清除当前线程的中断状态 isInterrupted 不会清除
  private static void interruptTest() throws InterruptedException {
    Runnable runnable =
        () -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
            // 重制中断位
          }
        };
    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(100L);
    // interruptTest:false
    System.out.println("interruptTest:" + thread.isInterrupted());
    thread.interrupt();
    Thread.sleep(100L);
    // interruptTest:false 阻塞状态中断位被捕获后会被自动清除
    System.out.println("interruptTest:" + thread.isInterrupted());
  }
}

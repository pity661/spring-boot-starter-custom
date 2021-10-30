package com.wenky.starter.custom.thread.exector.submit;

import java.util.concurrent.*;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-04 17:44
 */
public class ExecuteResultExample {
  public static void main(String[] args) throws Throwable {
    executeResultTest();
  }

  private static void executeResultTest() throws Throwable {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<String> future = executor.submit(() -> System.out.println("future"), "success");
    //        TimeUnit.MILLISECONDS.sleep(1000L);
    future.cancel(false);
    System.out.println(future.isDone());
    System.out.println(future.isCancelled());
    while (true && !future.isCancelled()) {
      if (future.isDone()) {
        System.out.println(future.get());
        break;
      }
    }

    Callable<String> callable =
        () -> {
          System.out.println("callable");
          return "success";
        };
    Future<String> future2 = executor.submit(callable);
    // 阻塞主线程直到任务完成
    try {
      System.out.println(future2.get());
    } catch (ExecutionException e) {
      Throwable origin = e.getCause();
      throw origin;
    }
    executor.shutdown();
  }
}

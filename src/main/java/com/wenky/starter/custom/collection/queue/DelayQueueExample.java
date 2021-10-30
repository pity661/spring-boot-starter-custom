package com.wenky.starter.custom.collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-04 16:52
 */
public class DelayQueueExample {

  public static void main(String[] args) throws InterruptedException {
    delayQueueTest();
  }

  private static void delayQueueTest() throws InterruptedException {
    DelayQueue<MyDelayedTask> delayQueue = new DelayQueue<>();
    delayQueue.offer(new MyDelayedTask("1", 1000L));
    delayQueue.offer(new MyDelayedTask("2", 2000L));
    delayQueue.offer(new MyDelayedTask("3", 3000L));
    delayQueue.offer(new MyDelayedTask("4", 4000L));
    delayQueue.offer(new MyDelayedTask("5", 5000L));

    while (!delayQueue.isEmpty()) {
      System.out.println(delayQueue.take());
    }
  }

  public static class MyDelayedTask implements Delayed {
    private String name;
    private Long start = System.currentTimeMillis();
    private Long time;

    public MyDelayedTask(String name, Long time) {
      this.name = name;
      this.time = time;
    }

    //
    @Override
    public long getDelay(TimeUnit unit) {
      return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
      return Math.toIntExact(
          this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
      return "MyDelayedTask{"
          + "name='"
          + name
          + '\''
          + ", start="
          + start
          + ", time="
          + time
          + '}';
    }
  }
}

package com.wenky.starter.custom.thread.exector.submit;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-29 17:08
 */
public class LockSupportExample {

    private static volatile Boolean TAG = Boolean.FALSE;

    public static void main(String[] args) throws InterruptedException {

        //        lockSupportTest();

        regularPrint();
    }

    // 1、必须在线程启动后，unpark无论在park前后执行都生效
    // 连续调用两次unpark相当于只生效一次(必须保证第一个park被unpark之后，unpark才会对第二个park生效)
    private static void lockSupportTest() throws InterruptedException {
        // 主线程调用 unlock
        Thread thread =
                new Thread(
                        () -> {
                            System.out.println("子线程阻塞");
                            /** [1]开始 先unpark后park */
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            LockSupport.park();
                            /** [1]结束 */

                            /** [2]开始 先park后unpark */
                            // 无限期暂停当前线程
                            // LockSupport.park();
                            // System.out.println("park1结束");
                            // TAG = Boolean.TRUE;
                            // LockSupport.park(); // 等上一个park释放后才能获取，被阻塞
                            /** [2]结束 */
                            System.out.println("子线程结束");
                        });

        // 必须在子线程start之后执行才有效
        thread.start();
        /** [1]开始 */
        LockSupport.unpark(thread);
        /** [1]结束 */

        /** [2]开始 */
        //        TimeUnit.SECONDS.sleep(1);
        //        System.out.println("休眠结束");
        //        LockSupport.unpark(thread);
        //        // 死循环，保证第二个park能被成功unpark
        //        while (!TAG) {}
        //        System.out.println("unpark2开始");
        //        LockSupport.unpark(thread);
        /** [2]结束 */
        System.out.println("主线程结束");
    }

    /**
     * 双线程规律打印
     *
     * @throws InterruptedException
     */
    public static void regularPrint() throws InterruptedException {
        Work work1 =
                new Work(
                        Boolean.TRUE,
                        Arrays.asList("a", "b", "c", "d", "e"),
                        Thread.currentThread());
        Thread thread1 = new Thread(work1);

        Work work2 = new Work(Boolean.FALSE, Arrays.asList(1, 2, 3, 4, 5), Thread.currentThread());
        Thread thread2 = new Thread(work2);

        work1.setParent(thread2);
        work2.setParent(thread1);

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();

        LockSupport.park();
    }

    public static class Work implements Runnable {
        private final Boolean first;
        private final List list;
        private Thread parent;
        private Thread main;

        public Work(Boolean first, List list, Thread main) {
            this.first = first;
            this.list = list;
            this.main = main;
        }

        @Override
        public void run() {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                // 非首任务直接解锁
                if (!first && i == 0) {
                    LockSupport.unpark(parent);
                }
                if (i != 0) {
                    LockSupport.unpark(parent);
                }

                // 结束main线程
                if (i < list.size() - 1) {
                    LockSupport.park();
                }
                if (!first && i >= list.size() - 1) {
                    LockSupport.unpark(main);
                }
            }
        }

        public Work setParent(Thread parent) {
            this.parent = parent;
            return this;
        }
    }
}

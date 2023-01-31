package com.wenky.starter.custom.frame.jvm;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-02 15:33
 */
public class GCExample {

    private static final int _1MB = 1024 * 1024;

    // -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    // -XX:+PrintCommandLineFlags
    public static void main(String[] args) {
        byte[] a1, a2, a3, a4, a5, a6, a7;

        //        System.gc();

        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        // 大对象直接进入老年代
        a4 = new byte[4 * _1MB];
        System.out.println(1);

        a5 = new byte[4 * _1MB];
        // 大对象不再使用时应该手动置null，便于系统垃圾回收
        a1 = null;
        a2 = null;
        a3 = null;
        a4 = null;
        a6 = new byte[4 * _1MB];
        a7 = new byte[4 * _1MB];
    }
}

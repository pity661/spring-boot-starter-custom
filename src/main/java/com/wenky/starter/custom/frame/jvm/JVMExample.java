package com.wenky.starter.custom.frame.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-02 10:54
 */
// -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class JVMExample {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        for (; ; ) {
            list.add(new JVMExample());
        }
    }
}

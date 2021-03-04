package com.wenky.starter.custom.util;

import java.util.Arrays;
import java.util.List;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 15:03
 */
public class ClassUtils {

  // 可在打印日志时动态获取日志所在位置信息
  public static void getMethodInfo() {
    // 方法的调用路径栈信息 class、method、fileName、line
    List<StackTraceElement> list = Arrays.asList(Thread.currentThread().getStackTrace());
    // 1. class:[java.lang.Thread] method:[getStackTrace] fileName:[Thread.java] line:[]
    // 2. class:[com.wenky.demo.util.ClassUtils] method:[getMethodInfo] fileName:[ClassUtils.java]
    // line:[]
    // 3. class:[com.wenky.demo.util.ClassUtils] method:[main] fileName:[ClassUtils.java] line:[]
    System.out.println(list.size());
  }
}

package com.wenky.starter.custom.util;

import java.util.Arrays;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 16:33
 */
public class LoggerUtils {
  private static final String TARGET_CLASS_NAME = LoggerUtils.class.getName();

  public static void construct() {
    info(getInvokeClassName() + " - 构造方法执行");
  }

  public static void info(String message) {
    getLogger().info(message);
  }

  public static void info(String message, Object object) {
    getLogger().info(String.format(message, object));
  }

  public static void exception(Exception e) {
    exception(getBizAction(), e);
  }

  public static void exception(String message, Throwable t) {
    getLogger().error(message, t);
  }

  public static void info(Object object) {
    getLogger().info(GsonUtils.toString(object));
  }

  // find the logging class
  private static Logger getLogger() {
    return LoggerFactory.getLogger(getInvokeClassName());
  }

  private static String getInvokeClassName() {
    String className =
        Arrays.stream(Thread.currentThread().getStackTrace())
            // skip the first one, thread class
            .skip(1)
            .filter(single -> !TARGET_CLASS_NAME.equals(single.getClassName()))
            .findFirst()
            .map(StackTraceElement::getClassName)
            .orElse(TARGET_CLASS_NAME);
    return className;
  }

  public static String getBizAction() {
    return Stream.of(Thread.currentThread().getStackTrace())
        .skip(1)
        .filter(single -> !TARGET_CLASS_NAME.equals(single.getClassName()))
        .findFirst()
        .map(element -> element.getClassName() + " - " + element.getMethodName())
        .get();
  }
}

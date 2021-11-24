package com.wenky.starter.custom.util.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-22 12:01
 */
public interface Printable {
  // reflect
  default String print() {
    Predicate<Method> predicate =
        method ->
            method.getName().startsWith("get")
                && Arrays.asList(String.class, Integer.class, Long.class)
                    .contains(method.getReturnType());
    Map<String, String> result =
        Stream.of(this.getClass().getDeclaredMethods())
            .filter(predicate)
            .collect(
                Collectors.toMap(
                    method -> method.getName(),
                    method -> {
                      try {
                        return String.valueOf(method.invoke(this));
                      } catch (IllegalAccessException e) {
                        e.printStackTrace();
                      } catch (InvocationTargetException e) {
                        e.printStackTrace();
                      }
                      return "null";
                    }));
    return result.entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining(", "));
  }
}

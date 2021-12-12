package com.wenky.starter.custom.basic.array;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-24 15:49
 */
public class ArrayCopyExample {

  public static void main(String[] args) {
    String[] a = {"1", "2"};
    // 12
    System.out.println(Stream.of(a).collect(Collectors.joining()));
    String[] b = a.clone();
    b[0] = "2";
    // 22
    System.out.println(Stream.of(b).collect(Collectors.joining()));
    String[] c = Arrays.copyOf(b, b.length);
    // 22
    System.out.println(Stream.of(c).collect(Collectors.joining()));
    String[] d = new String[2];
    System.arraycopy(c, 0, d, 0, 2);
    // 22
    System.out.println(Stream.of(d).collect(Collectors.joining()));
  }
}

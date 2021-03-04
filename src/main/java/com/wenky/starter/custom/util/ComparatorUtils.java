package com.wenky.starter.custom.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-21 10:21
 */
public class ComparatorUtils {

  public static void main(String[] args) {
    thenComparing();

    // mapSort();
  }

  public static void mapSort() {
    Map<String, String> map = new HashMap<>();
    map.put("b", "");
    map.put("a", "");
    map.put("e", "");
    map.put("c", "");
    map.put("d", "");
    String string =
        map.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(","));
    System.out.println(string);
  }

  public static void thenComparing() {
    List<Bob> list =
        Arrays.asList(
            new Bob(6, null),
            new Bob(7, null),
            new Bob(1, "aa"),
            new Bob(1, "bb"),
            new Bob(3, "aa"),
            new Bob(2, "aa"),
            null);
    // 排序
    Comparator<Bob> comparator =
        // 1.如果b值为null排在最后
        // 2.如果第一次排序值一样返回0，通过第二个条件进行排序
        // 3.a为aa则放到后面
        Comparator.comparing(
            Bob::getB, Comparator.nullsLast((a, b) -> a.equals(b) ? 0 : a.equals("aa") ? -1 : 1));
    // thenComparing 会返回一个新的对象，不能直接用原来的对象处理
    // 先按照b排序，然后按照a正序
    // comparator = comparator.thenComparing(Bob::getA);
    // 先按照b排序，然后按照a倒序
    comparator = comparator.thenComparing(Comparator.comparing(Bob::getA).reversed());
    // Bob的null对象排在最前面
    comparator = Comparator.nullsFirst(comparator);
    list.sort(comparator);
    list.forEach(System.out::println);
  }

  public static class Bob {
    private Integer a;
    private String b;

    public Bob(Integer a, String b) {
      this.a = a;
      this.b = b;
    }

    public Integer getA() {
      return a;
    }

    public String getB() {
      return b;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Bob{");
      sb.append("a=").append(a);
      sb.append(", b='").append(b).append('\'');
      sb.append('}');
      return sb.toString();
    }
  }
}

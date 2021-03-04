package com.wenky.starter.custom.util;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-15 11:46
 */
public class ListUtils {
  /**
   * 按照指定大小分割数组
   *
   * @param list
   * @param size
   * @param <T>
   * @return
   */
  public <T> List<List<T>> partition(List<T> list, Integer size) {
    return Lists.partition(list, size);
  }
}

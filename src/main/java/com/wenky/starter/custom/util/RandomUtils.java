package com.wenky.starter.custom.util;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-23 18:23
 */
public class RandomUtils {
  /**
   * 取min-max区间的随机数
   *
   * @param min
   * @param max
   * @return
   */
  public static int random(int min, int max) {
    int s = new Random().nextInt(max) % (max - min + 1) + min;
    return s;
  }

  /**
   * 随机指定长度数字、字符串
   *
   * @param count
   * @return
   */
  public static String randomString(int count) {
    return RandomStringUtils.randomAlphanumeric(count);
  }

  /**
   * 随机布尔值
   *
   * @return
   */
  public static boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  public static void main(String[] args) {
    System.out.println(randomString(10));
  }
}

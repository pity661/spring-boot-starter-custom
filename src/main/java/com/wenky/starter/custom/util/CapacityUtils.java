package com.wenky.starter.custom.util;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-22 09:31
 */
public class CapacityUtils {
  public static String null2Empty(String s) {
    return s == null ? "" : s;
  }

  //
  public static BigDecimal getNotNullBigDecimal(String number) {
    if (StringUtils.isNumeric(number)) {
      return new BigDecimal(number);
    }
    return getInitBigDecimal();
  }

  private static BigDecimal getInitBigDecimal() {
    return new BigDecimal("0.00");
  }
}

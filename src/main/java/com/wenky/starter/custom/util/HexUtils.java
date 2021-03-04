package com.wenky.starter.custom.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Hex;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-20 11:20
 */
public class HexUtils {
  public String encodeHexString(String str) {
    return Hex.encodeHexString(str.getBytes(StandardCharsets.UTF_8));
  }

  public String encodeHexStringDefault(String str) {
    return String.format("%x", new BigInteger(1, str.getBytes(StandardCharsets.UTF_8)));
  }
}

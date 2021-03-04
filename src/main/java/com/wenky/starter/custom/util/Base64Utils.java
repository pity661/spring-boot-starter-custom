package com.wenky.starter.custom.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-21 11:20
 */
public class Base64Utils {

  public static String encode(String string) {
    return Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
  }

  public static String decode(String string) {
    return new String(Base64.getDecoder().decode(string.getBytes(StandardCharsets.UTF_8)));
  }

  public static String urlEncode(String url) {
    return Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
  }

  public static String urlDecode(String url) {
    return new String(Base64.getUrlDecoder().decode(url.getBytes(StandardCharsets.UTF_8)));
  }

  public static void main(String[] args) {
    String wenky = "wenky";
    System.out.println(encode(wenky));
    System.out.println(decode(encode(wenky)));

    String url = "http://123.123.123.123:80?key=value";
    System.out.println(urlEncode(url));
    System.out.println(urlDecode(urlEncode(url)));
  }
}

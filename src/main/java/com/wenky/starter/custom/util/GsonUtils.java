package com.wenky.starter.custom.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 13:48
 */
public class GsonUtils {

  public static final Gson GSON =
      new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .disableHtmlEscaping()
          // null属性也会保留
          .serializeNulls()
          .create();
  public static final Gson GSON_UNDERLINE =
      new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .disableHtmlEscaping()
          .serializeNulls()
          // 字符串格式化输出
          .setPrettyPrinting()
          .create();

  // "null" -> null | null -> null
  public static <T> T str2Class(@Nullable String string, Class<T> t) {
    return GSON.fromJson(string, t);
  }

  public static <T> T str2Class(String string, Type type) {
    return GSON.fromJson(string, type);
  }

  public static <T> T str2Class(String string, TypeToken<T> t) {
    return str2Class(string, t.getType());
  }

  public static Map<String, Object> str2Map(String string) {
    return str2Class(string, new TypeToken<HashMap<String, Object>>() {});
  }

  // key -> value
  public static Map<String, Object> obj2Map(@Nullable Object object) {
    return str2Class(obj2String(object), new TypeToken<HashMap<String, Object>>() {});
  }

  // null -> "null" | "null" -> "null"
  public static String obj2String(@Nullable Object object) {
    return GSON.toJson(object);
  }

  public static <T> T obj2TargetObj(@NonNull Object object, Class<T> t) {
    return str2Class(obj2String(object), t);
  }

  public static String toString(Object object) {
    return obj2String(object);
  }
}

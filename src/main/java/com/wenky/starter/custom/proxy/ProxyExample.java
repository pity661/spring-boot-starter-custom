package com.wenky.starter.custom.proxy;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 克林
 * @date 2025/7/24 16:17
 */
public class ProxyExample {

    private static final List<String> value = Arrays.asList("1");

    public static void main(String[] args) {
        ProxyExample proxyExample = new ProxyExample();
        System.out.println(proxyExample.value);
        Field[] fields = ProxyExample.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class) == Boolean.FALSE) {
                continue;
            }
            try {
                field.setAccessible(Boolean.TRUE);
                Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(Boolean.TRUE);
                // final类型
                modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                FieldUtils.writeStaticField(field, Arrays.asList("2"));
                modifiers.setInt(field, field.getModifiers() | ~Modifier.FINAL);
                modifiers.setAccessible(Boolean.FALSE);
                field.setAccessible(Boolean.FALSE);

//                ClassUtils.getAllInterfaces();

//                System.out.println(field.getChar(proxyExample) + "123");
            } catch (Exception e) {
                // do nothing
                System.out.println(e);
            }
        }
        List<String> value = proxyExample.value;
        System.out.println(value);
    }

}

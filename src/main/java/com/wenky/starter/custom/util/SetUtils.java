package com.wenky.starter.custom.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-22 10:17
 */
public class SetUtils {
    // 并集
    public static <T> Set<T> addAll(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // 交集
    public static <T> Set<T> retainAll(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    // 差集
    public static <T> Set<T> removeAll(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    // 并集 - 交集 - 差集
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return removeAll(addAll(a, b), retainAll(a, b));
    }
}

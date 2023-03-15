package com.wenky.starter.custom.frame.cache;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2023-02-20 17:24
 */
public class WeakReferenceExample {

    // 强引用 软引用 弱引用 虚引用

    // 弱引用例子 当发生gc时，对象就会被垃圾回收
    public static void main(String[] args) {
        Map<WeakReference<WeakReferenceExample>, String> map = new HashMap<>();
        WeakReferenceExample key = new WeakReferenceExample();
        String value = "value";

        WeakReference<WeakReferenceExample> weakKey = new WeakReference(key);
        map.put(weakKey, value);
        key = null;
        value = null;

        System.gc();

        System.out.println(weakKey.get() == null);
        // 1
        System.out.println(map.keySet().size());
        // 1
        System.out.println(map.values().size());
    }
}

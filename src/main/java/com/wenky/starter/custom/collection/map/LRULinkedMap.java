package com.wenky.starter.custom.collection.map;

import java.util.*;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-05 18:36
 */
public class LRULinkedMap<K, V> {
    private int cacheSize;

    private Map<K, V> cacheMap;

    // synchronized instance
    private LRULinkedMap(int cacheSize) {
        this.cacheSize = cacheSize;

        // 构造时指定accessOrder为true，调整活跃元素顺序
        cacheMap =
                Collections.synchronizedMap(
                        new LinkedHashMap<K, V>(cacheSize, 0.75f, Boolean.TRUE) {
                            // 判断是否删除最不活跃的元素
                            @Override
                            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                                if (cacheSize + 1 == cacheMap.size()) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
    }

    // 构造线程安全的
    public static LRULinkedMap newInstanceSynchronized(int cacheSize) {
        return new LRULinkedMap(cacheSize);
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    public List<Map.Entry<K, V>> getAll() {
        return new ArrayList<Map.Entry<K, V>>(cacheMap.entrySet());
    }

    public static void main(String[] args) {
        //        lruLinkedMapTest();
        System.out.println(logTest(10));
    }

    private static int logTest(int n) {
        // 计算结果log以2为底10的指数 （Math.log - log以e为底）
        double m = Math.log(n) / Math.log(2);
        System.out.println("m:" + m);
        // 向上取整，小数部位直接舍去
        int m2 = (int) Math.ceil(m);
        // m2: 4
        System.out.println("m2:" + m2);
        // 2^4 = 16
        return (int) Math.pow(2, m2);
    }

    private static void lruLinkedMapTest() {
        LRULinkedMap<String, String> lruLinkedMap = newInstanceSynchronized(3);
        lruLinkedMap.put("1", "1");
        lruLinkedMap.put("2", "2");
        lruLinkedMap.put("3", "3");
        System.out.println(lruLinkedMap.getAll());
        lruLinkedMap.put("4", "4");
        System.out.println(lruLinkedMap.getAll());
        lruLinkedMap.get("2");
        System.out.println(lruLinkedMap.getAll());
        lruLinkedMap.put("1", "1");
        System.out.println(lruLinkedMap.getAll());
    }
}

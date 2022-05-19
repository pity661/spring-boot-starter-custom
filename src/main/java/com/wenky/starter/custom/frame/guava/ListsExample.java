package com.wenky.starter.custom.frame.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2021-12-30 10:31
 */
public class ListsExample {

    public static void partition() {
        // new Random().ints()
        List<Integer> list =
                IntStream.range(0, 10)
                        .limit(10)
                        .mapToObj(Integer::valueOf)
                        .collect(Collectors.toList());
        System.out.println(list);

        List<List<Integer>> partitionList = Lists.partition(list, 3);
        System.out.println("partition size:" + partitionList.size());
        System.out.println(partitionList);
    }

    // 交集
    public static void intersection() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3);
        Set<Integer> set2 = Sets.newHashSet(1, 2);

        Sets.SetView<Integer> integerSetView = Sets.intersection(set1, set2);
        integerSetView.forEach(System.out::println);
    }

    public static void main(String[] args) {
        //        partition();
        intersection();
    }
}

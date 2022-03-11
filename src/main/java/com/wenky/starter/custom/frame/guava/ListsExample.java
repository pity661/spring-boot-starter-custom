package com.wenky.starter.custom.frame.guava;

import com.google.common.collect.Lists;
import java.util.List;
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

    public static void main(String[] args) {
        partition();
    }
}

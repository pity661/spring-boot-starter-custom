package com.wenky.starter.custom.frame.lambda;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-28 14:30
 */
public class OptionalExample {
    // map 返回的结果是 string
    // flatMap 返回的结果是 Optional<String>
    public static void main(String[] args) {
        String result =
                Optional.of("a")
                        .map(a -> a + "b")
                        .flatMap(a -> Optional.ofNullable(a).map(x -> x + "b"))
                        .orElse(null);
        System.out.println(result);

        BiFunction<Integer, Integer, Integer> addFunction = Integer::sum;
    }
}

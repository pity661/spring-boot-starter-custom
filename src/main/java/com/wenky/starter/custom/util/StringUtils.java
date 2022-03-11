package com.wenky.starter.custom.util;

import java.util.stream.Stream;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2021-12-30 10:38
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String regexSplit(String source, String regex) {
        return Stream.of(source.split(regex))
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .orElse(null);
    }

    public static String range(Integer size) {
        if (size == null || size < 0) {
            return "";
        }
        return RandomStringUtils.randomAlphanumeric(size);
    }

    public static String rangeNumeric(Integer size) {
        if (size == null || size < 0) {
            return "";
        }
        // new Random().ints(0, 9).limit(size)
        // .mapToObj(String::valueOf).collect(Collectors.joining());
        return RandomStringUtils.randomNumeric(size);
    }

    public static void main(String[] args) {
        //    System.out.println(range(10));
        //    System.out.println(rangeNumeric(10));

        // a
        System.out.println(regexSplit("a////a", "^.*/"));
        // ///a
        System.out.println(regexSplit("a////a", "^.*?/"));
    }
}

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

    private static final char[] CHAR_ARG = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 生成不重复的随机字符串
     *
     * @param number
     * @param machineCode
     * @return
     */
    public static String uniqueCode(Long number, String machineCode) {
        //        Long increaseNumber = System.currentTimeMillis() + number;
        Long increaseNumber = overturn(System.currentTimeMillis() + number);
        // 翻转之后再加个位数
        increaseNumber += number % 10;
        String result = getUniqueCode(increaseNumber);
        return result;
    }

    private static Long overturn(Long number) {
        String value = String.valueOf(number);
        char[] chars = value.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = chars.length - 1; index >= 0; --index) {
            stringBuilder.append(chars[index]);
        }
        return Long.valueOf(stringBuilder.toString());
    }

    private static String getUniqueCode(Long increaseNumber) {
        Integer argLength = CHAR_ARG.length;
        // 倍数
        Long multiple = increaseNumber / argLength;
        // 余数
        Integer remainder = Math.toIntExact(increaseNumber % argLength);
        if (multiple >= argLength) {
            return getUniqueCode(multiple) + CHAR_ARG[remainder];
        }
        return String.valueOf(CHAR_ARG[Math.toIntExact(multiple)] + CHAR_ARG[remainder]);
    }

    public static String regexSplit(String source, String regex) {
        return Stream.of(source.split(regex))
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .orElse(null);
    }

    /**
     * 随机字符串生成
     *
     * @param size
     * @return
     */
    public static String range(Integer size) {
        if (size == null || size < 0) {
            return "";
        }
        return RandomStringUtils.randomAlphanumeric(size);
    }

    /**
     * 随机数字生成
     *
     * @param size
     * @return
     */
    public static String rangeNumeric(Integer size) {
        if (size == null || size < 0) {
            return "";
        }
        // new Random().ints(0, 9).limit(size)
        // .mapToObj(String::valueOf).collect(Collectors.joining());
        return RandomStringUtils.randomNumeric(size);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(range(5));
        System.out.println(rangeNumeric(10));

        Long millis = System.currentTimeMillis();
        System.out.println(millis);
        System.out.println(overturn(millis));
        System.out.println(uniqueCode(1L, "a"));
        System.out.println(uniqueCode(12L, "a"));
        Thread.sleep(50);
        System.out.println(uniqueCode(100L, "a"));
        Thread.sleep(10);
        System.out.println(uniqueCode(1000L, "a"));

        //        Thread.sleep(100);
        //        System.out.println(uniqueCode(50000L, "a"));
        //        Thread.sleep(200);
        //        System.out.println(uniqueCode(3L, "a"));
        //    System.out.println(range(10));
        //    System.out.println(rangeNumeric(10));

        // a
        System.out.println(regexSplit("a////a", "^.*/"));
        // ///a
        System.out.println(regexSplit("a////a", "^.*?/"));

        int a = 1000;

        System.out.println(a == 1000);
        System.out.println(a == Integer.valueOf(1000));
        System.out.println(a == new Integer(1000));
    }
}

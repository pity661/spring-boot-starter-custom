package com.wenky.starter.custom.util;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-15 11:46
 */
public class ListUtils {
    /**
     * 按照指定大小分割数组
     *
     * @param list
     * @param size
     * @param <T>
     * @return
     */
    public <T> List<List<T>> partition(List<T> list, Integer size) {
        return Lists.partition(list, size);
    }

    /**
     * 分页
     *
     * @param source
     * @param currentPage
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<T> page(List<T> source, Integer currentPage, Integer pageSize) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.EMPTY_LIST;
        }
        if (currentPage == null || currentPage < 1) {
            throw new IllegalArgumentException("页数: " + currentPage);
        }
        if (pageSize == null) {
            throw new IllegalArgumentException("分页大小: " + pageSize);
        }
        Integer fromIndex = (currentPage - 1) * pageSize;
        if (fromIndex > source.size()) {
            return Collections.EMPTY_LIST;
        }
        Integer toIndex = currentPage * pageSize;
        if (toIndex > source.size()) {
            toIndex = source.size();
        }
        return source.subList(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        //        Object[] objects = new Object[]{"a", 1};
        //        System.out.println(Stream.of(objects).findFirst().orElse(null));
        //
        //        Method method =
        // Stream.of(ListUtils.class.getDeclaredMethods()).findFirst().orElse(null);
        //        System.out.println(method.getName());

        System.out.println(invoke());
    }

    public static String invoke() {
        String result = null;
        try {
            result = null;
        } finally {
            if (StringUtils.isBlank(result)) {
                result = "a";
            }
        }
        return result;
    }
}

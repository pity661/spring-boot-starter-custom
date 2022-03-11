package com.wenky.starter.custom.collection.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-20 15:10
 */
public class SortExample {

    public static void main(String[] args) {
        List<Instance> instanceList =
                Arrays.asList(
                        null,
                        Instance.newInstance(1L, 5),
                        null,
                        Instance.newInstance(2L, null),
                        Instance.newInstance(3L, 1),
                        Instance.newInstance(4L, 3),
                        Instance.newInstance(5L, null),
                        Instance.newInstance(6L, 1));
        // 如果对象为null，直接排在最后面
        // 然后根据sort正序排列，如果sort是null排在最前面
        // 最后如果sort一样，根据id倒叙排列
        Comparator<Instance> simpleComparator =
                Comparator.nullsLast(
                        Comparator.comparing(
                                        Instance::getSort, Comparator.nullsFirst(Integer::compare))
                                .thenComparing(
                                        Comparator.comparing(Instance::getId, Long::compare)
                                                .reversed()));
        instanceList.sort(simpleComparator);
        System.out.println(instanceList);
    }

    public static class Instance {
        private Long id;
        private Integer sort;

        public static Instance newInstance(Long id, Integer sort) {
            Instance instance = new Instance();
            instance.setId(id);
            instance.setSort(sort);
            return instance;
        }

        public Long getId() {
            return id;
        }

        public Instance setId(Long id) {
            this.id = id;
            return this;
        }

        public Integer getSort() {
            return sort;
        }

        public Instance setSort(Integer sort) {
            this.sort = sort;
            return this;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Instance.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("sort=" + sort)
                    .toString();
        }
    }
}

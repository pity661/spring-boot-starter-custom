package com.wenky.starter.custom.util.reflect.clazz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-08-17 10:14
 */
public class ClassType extends AbstractType<Integer> {

    // 获取范型对象类型信息
    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final int index) {
        // genType => AbstractType<Integer>
        Type genType = clazz.getGenericSuperclass();
        // 是否为参数化类型
        // AbstractType<Integer> true
        // AbstractType false
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index < 0 || index >= params.length) {
            return Object.class;
        }
        Type type = params[index];
        // 可能是T类型的
        if (!(type instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) type;
    }

    public static void main(String[] args) {
        //        Boolean check = (getSuperClassGenericType(ClassType.class, 0) == Integer.class);
        //        System.out.println(check);

        Boolean check = (getSuperClassGenericType(ClassType1.class, 0) == Integer.class);
        System.out.println(check);
    }

    public static class ClassType1<T> extends AbstractType<T> {}
}

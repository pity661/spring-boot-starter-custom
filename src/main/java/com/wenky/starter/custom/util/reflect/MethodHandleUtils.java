package com.wenky.starter.custom.util.reflect;

import com.wenky.starter.custom.util.LoggerUtils;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-22 11:09
 */
public class MethodHandleUtils {
    public static void people()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object object = new People();
        // getMethod 仅当前类中的方法，且需为public
        object.getClass().getMethod("setAge", Integer.class).invoke(object, 27);
        // getDeclaredMethod 是所有方法，包括父类
        object.getClass().getDeclaredMethod("setName", String.class).invoke(object, "wenky");

        LoggerUtils.info(object);
    }

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        people();
    }
}

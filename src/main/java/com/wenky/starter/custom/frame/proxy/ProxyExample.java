package com.wenky.starter.custom.frame.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-06 11:07
 */
public class ProxyExample {

    public static void main(String[] args) {
        //        proxyTest();
        returnTypeTest();
    }

    public static void proxyTest() {
        //        Object animal =
        // Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        //                new Class[] {Animal.class}, new CustomInvocationHandler(() -> "dog
        // play"));
        //
        //        Animal dog = (Animal) animal;
        //        // proxy
        //        System.out.println(dog.play());
        //        System.out.println("****************");
        //        // not proxy
        //        System.out.println(dog.hashCode());
    }

    public static void returnTypeTest() {
        Method[] methods = Animal.class.getMethods();
        System.out.println(methods.length);

        Class<?> clazz =
                Stream.of(methods)
                        .filter(method -> "cry".equals(method.getName()))
                        .findFirst()
                        .get()
                        .getReturnType();

        System.out.println(clazz.equals(Void.class));
        System.out.println(clazz.isPrimitive());
        System.out.println(clazz.getName());
    }

    public interface Animal {
        String play();
        // true
        // false
        // java.lang.Void
        //        Void cry();

        // false
        // true
        // void
        void cry();
    }

    public static class CustomInvocationHandler implements InvocationHandler {

        private static final List<Method> OBJECT_METHODS = Arrays.asList(Object.class.getMethods());

        private Animal animal;

        CustomInvocationHandler(Animal animal) {
            Objects.requireNonNull(animal);
            this.animal = animal;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Object 内置函数不处理
            if (OBJECT_METHODS.contains(method)) {
                // (instance, params)
                return method.invoke(animal, args);
            }

            System.out.println("proxy");

            return method.invoke(animal, args);
        }
    }
}

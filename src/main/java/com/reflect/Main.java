package com.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenfu on 2017/3/31.
 */
public class Main {

    public static void main(String[] args) {

        final Clz clz = new Clz();
        Class clazz = clz.getClass();
        Itf itf = (Itf) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                System.out.println("before method excute!");
                if (method.getDeclaringClass() == Object.class) {
                    result = method.invoke(proxy, args);
                } else {
                    result = method.invoke(clz, args);
                }

                System.out.println("after method excute!");

                return result;
            }
        });


        itf.printSth("me");

    }
}

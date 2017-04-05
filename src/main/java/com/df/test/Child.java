package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/15
 */
public class Child extends Parent {

    public String name = "Child";

    public Child() {
        System.out.println(name);
    }

    public Child(int i) {
        System.out.println("Child [" + i + "]");
        System.out.println(name);
    }

    public static void hello() {  // 大坑 static方法
        System.out.println("Parent Child");
    }

    public class A {
        public A() {
            System.out.println("Child A");
        }
    }


    public static class B {
        public B() {
            System.out.println("Child B");
        }
    }


    final String getName() {
        return name;
    }

}

package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/15
 */
public class Parent {

    public String name = "ppppppp";

    public Parent() {
        System.out.println(name);
    }

    public Parent(int i) {
        System.out.println("parent [" + i + "]");
        System.out.println(name);
    }


    public static void hello() {  // 大坑 static方法
        System.out.println("Parent hello");
    }

    public class A {
        public A() {
            System.out.println("parent A");
        }
    }


    public static class B {
        public B() {
            System.out.println("parent B");
        }
    }

     String getName() {
        return name;
    }

}

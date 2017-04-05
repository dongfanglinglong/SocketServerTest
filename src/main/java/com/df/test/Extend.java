package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/24
 */
public class Extend {
    static class Animal {
        public void dosome() {
            System.out.println("Animal do");
        }

        public void test(Animal c) {
            System.out.println("Animal test");
        }
    }


    static class Cat extends Animal {
        public void dosome() {
            System.out.println("Cat do");
        }

        public void test(Cat c) {
            System.out.println("Cat test");
        }
    }

    public static void main(String[] args) {
        Animal a = new Cat();
        Cat c = new Cat();
        a.test(c);
        a.dosome();
    }

}

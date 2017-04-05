package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/25
 */
public class Polymorphism {

    private static class Animal {
        public void dosome() {
            System.out.println("animal dosome");
        }

        public void test(Animal a) {
            System.out.println("animal test");
        }
    }

    private static class Cat extends Animal {

        public void dosome() {
            System.out.println("Cat dosome");
        }

        public void test(Cat c) {
            System.out.println("Cat test");
        }
    }


    public static void main(String[] args) {
        Animal a = new Cat();
        a.dosome(); // Cat dosome
        a.test(a); //animal test

        Cat c = new Cat();
        a.test(c); //animal test
        c.test(c); //Cat test
        c.test(a); //animal test
    }
}

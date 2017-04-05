package com.df.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Auth dongfang
 * Date 2017/3/28
 */
public class Counter {
    public volatile static int count = 0;

    public static void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        count++;
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            System.out.println("i[" + i + "]");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Counter.inc();
                }
            }).start();
        }

        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + Counter.count);
    }


    public static Unsafe getUnsafe() {
        try {

            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

package com.df.test;

/**
 * Auth dongfang
 * Date 2017/3/25
 */
public class PrintThread {

    private static int state = 0;

    static class PrintThd extends Thread {
        private int num;
        Object lock;

        public PrintThd(int i, Object lock) {
            num = i;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (state < 30) {
                    if (state % 3 == num) {
                        System.out.print(num+1);
                        state++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        new PrintThd(0, lock).start();
        new PrintThd(1, lock).start();
        new PrintThd(2, lock).start();
    }
}

package com.df.ProdAndCon;

/**
 * Auth dongfang
 * Date 2017/3/27
 */
public class PAndC {

    static class P implements Runnable {

        private Proxy proxy;
        private String name;

        public P(Proxy proxy, String name) {
            this.proxy = proxy;
            this.name = name;
        }


        @Override
        public void run() {
            try {
                proxy.produce(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    static class C implements Runnable {
        private Proxy proxy;
        private String name;

        public C(Proxy proxy, String name) {
            this.proxy = proxy;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                proxy.consumer(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Proxy {
        private int foodNum = 0;
        private Object object = new Object();
        private int MAX = 5;


        public void produce(String name) throws InterruptedException {
            synchronized (object) {
                while (foodNum > MAX) {
                    System.out.println(name + " produce full!!!!!!!!!!!!!!!");
                    object.wait();
                }
                foodNum++;
                System.out.println(name + " produce food[" + foodNum + "]");
                object.notifyAll();
            }
        }


        public void consumer(String name) throws InterruptedException {
            synchronized (object) {
                while (foodNum < 1) {
                    System.out.println(name + " consumer empty!!!!!!!!!!!!!!!!!");
                    object.wait();
                }
                System.out.println(name + " consumer food[" + foodNum + "]");
                foodNum--;
                object.notifyAll();
            }
        }


    }


    public static void main(String[] args) {

        Proxy proxy = new Proxy();

        new Thread(new C(proxy,"消费者1")).start();
        new Thread(new C(proxy,"消费者2")).start();
        new Thread(new C(proxy,"消费者3")).start();
        new Thread(new C(proxy,"消费者4")).start();
        new Thread(new C(proxy,"消费者5")).start();
        new Thread(new C(proxy,"消费者6")).start();
        new Thread(new C(proxy,"消费者7")).start();

        new Thread(new P(proxy,"生产者1")).start();
        new Thread(new P(proxy,"生产者2")).start();
        new Thread(new P(proxy,"生产者3")).start();
        new Thread(new P(proxy,"生产者4")).start();
        new Thread(new P(proxy,"生产者5")).start();
        new Thread(new P(proxy,"生产者6")).start();
        new Thread(new P(proxy,"生产者7")).start();





    }
}

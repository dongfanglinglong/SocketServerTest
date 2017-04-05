package com.df.ProdAndCon;

import java.util.concurrent.BlockingQueue;

/**
 * Auth dongfang
 * Date 2017/3/25
 */
public class Consumer implements Runnable {

    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Consumer [" + queue.take() + "]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

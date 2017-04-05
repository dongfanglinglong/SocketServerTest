package com.df.ProdAndCon;

import java.util.concurrent.BlockingQueue;

/**
 * Auth dongfang
 * Date 2017/3/25
 */
public class Producter implements Runnable {
    BlockingQueue<String> queue;

    public Producter(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            String temp = this.toString();
            System.out.println("Producter[" + temp + "]");
            queue.put(temp);//如果队列是满的话，会阻塞当前线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
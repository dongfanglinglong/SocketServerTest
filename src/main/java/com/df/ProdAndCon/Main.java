package com.df.ProdAndCon;


import com.google.gson.annotations.Since;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Auth dongfang
 * Date 2017/3/25
 */
@Since(1.6)
public class Main {


    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>(2);


        Producter producter = new Producter(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        for (int i = 0; i < 5; i++) {
            new Thread(producter, "producter" + i).start();
            new Thread(consumer, "producter" + i).start();
        }

    }
}

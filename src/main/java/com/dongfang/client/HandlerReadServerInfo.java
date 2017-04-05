package com.dongfang.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by dongfang on 2016/3/30.
 */
public class HandlerReadServerInfo extends Thread {
    private Socket client;

    BufferedReader buf;

    public HandlerReadServerInfo(Socket client) {
        this.client = client;
        try {
            buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        super.run();
        try {
            boolean flag = true;
            while (flag) {
                String echo = buf.readLine();
                System.out.println(null == echo || "".equals(echo) ? "-null-" : echo);
                if (null != echo && echo.contains("bye")) {
                    flag = false;
                }
            }
            close();
        } catch (SocketTimeoutException e) {
            System.out.println("Time out, No response");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    private void close() {
        try {
            buf.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

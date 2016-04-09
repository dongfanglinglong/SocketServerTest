package com.dongfang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by dongfang on 2016/3/30.
 */
public class HandlerReadCMDInfo extends Thread {
    private Socket client;
    private String type;

    PrintStream out;
    BufferedReader input;

    public HandlerReadCMDInfo(Socket client, String type) {
        this.client = client;
        this.type = type;

        try {
            out = new PrintStream(client.getOutputStream());
            input = new BufferedReader(new InputStreamReader(System.in));
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
                System.out.println(type + "输入信息：");
                String strInput = input.readLine();
                if (strInput != null && !"".equals(strInput)) {
                    out.println(type + strInput);
                }

                if ("bye".equals(strInput)) {
                    flag = false;
                }
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    private void close() {
        try {
            input.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

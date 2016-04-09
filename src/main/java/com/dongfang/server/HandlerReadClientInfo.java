package com.dongfang.server;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by dongfang on 2016/3/30.
 */
public class HandlerReadClientInfo extends Thread {
    private Socket client;

    private BaseBean mBean;

    SocketMegBean mSocketMegBean;
    // 从Client读取数据
    BufferedReader readerClientInfo;

    // 写入数据到client
    PrintStream outToClient;

    public static final String UI_ID = "DF4S_";

    public HandlerReadClientInfo(Socket client) {
        mBean = new BaseBean();
        mBean.id = 0;
        mBean.msg = "ok";
        mBean.data = "heart";


        mSocketMegBean = new SocketMegBean();
        mSocketMegBean.id = UI_ID;
        mSocketMegBean.mstType = SocketMegBean.MSG_TYPE_SOCKET;
        mSocketMegBean.msg = "OK";
        mSocketMegBean.data = "{\"a\":10}";
        mSocketMegBean.dataArrary = new String[]{"1", "2", "3", "4"};

        this.client = client;
        try {
            readerClientInfo = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outToClient = new PrintStream(client.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void run() {
        super.run();
        if (readerClientInfo == null) return;
        try {
            String clientInfo = "", str = "";
            boolean flag = true;
            while (flag) {
                clientInfo = readerClientInfo.readLine();
                if (null != clientInfo) {
                    str = "c2s [" + ("".equals(clientInfo) ? "-null-" : clientInfo) + "]";
                    System.out.println(str);

                    if (str.contains("LANG_H")) {
                        String l = str.substring(str.indexOf("num=") + 4, str.indexOf("&"));
                        if (null != outToClient) {
                            mBean.id = Long.parseLong(l);
                            str = new Gson().toJson(mBean);
                            System.out.println("To JSON --> " + str);
                            outToClient.println(str);
                        }
                    } else if (str.contains("LANG_MSG")) {
                        mSocketMegBean.id = UI_ID + System.currentTimeMillis() % 20;
                        str = new Gson().toJson(mSocketMegBean);
                        System.out.println("To JSON --> " + str);
                        outToClient.println(str);
                    } else if ("bye".equals(clientInfo)) {
                        flag = false;
                    }
                }
            }

            close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    private void close() {
        try {
            outToClient.close();
            readerClientInfo.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

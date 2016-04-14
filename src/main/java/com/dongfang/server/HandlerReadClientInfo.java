package com.dongfang.server;

import com.dongfang.bean.BaseBean;
import com.dongfang.bean.SocketMsgBean;
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

    SocketMsgBean mSocketMegBean;
    // 从Client读取数据
    BufferedReader readerClientInfo;

    // 写入数据到client
    PrintStream outToClient;

    public static final long UI_ID = 888000;

    public HandlerReadClientInfo(Socket client) {
        mBean = new BaseBean();
        mBean.id = 1100;
        mBean.msg = "ok";
        mBean.data = "heart";


        mSocketMegBean = new SocketMsgBean();
        mSocketMegBean.msgId = UI_ID;
        mSocketMegBean.mstType = SocketMsgBean.MSG_TYPE_SOCKET;
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

    /**
     * SOCKET 心跳数据
     */
    private static final String HEART_ACTION = "LANG_H";

    public static final String SOCKET_ACTION_TEST = "TEST";
    public static final String SOCKET_ACTION_FEEDBACK = "FEEDBACK";

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

                    if (str.contains(HEART_ACTION)) {
                        String l = str.substring(str.indexOf("num=") + 4, str.indexOf("&"));
                        if (null != outToClient) {
                            mBean.id = Long.parseLong(l);
                            if (mBean.id % 3 == 0) {
                                mBean.id = UI_ID + System.currentTimeMillis() % 20;
                                mSocketMegBean.msgId = mBean.id;
                                mBean.data = new Gson().toJson(mSocketMegBean);
                                str = new Gson().toJson(mBean);
                                System.out.println("To JSON --> " + str);
                                outToClient.println(str);
                            } else {
                                mBean.data = "heart";
                                str = new Gson().toJson(mBean);
                                System.out.println("To JSON --> " + str);
                                outToClient.println(str);
                            }
                        }
                    } else if (str.contains(SOCKET_ACTION_TEST)) {
                        mBean.id = UI_ID + System.currentTimeMillis() % 20;
                        mBean.data = new Gson().toJson(mSocketMegBean);
                        str = new Gson().toJson(mBean);
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

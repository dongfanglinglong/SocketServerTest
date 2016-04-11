package com.dongfang.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
* Created by dongfang on 2016/3/30.
        */
public class MultiThreadServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(20011);
        Socket client = null;
        boolean flag = true;

        while (flag) {
            System.out.println("服务器端等待客户端发起连接请求");
            client = server.accept();
            System.out.println("客户端向服务器端发起了连接请求,且连接成功");
            new HandlerReadCMDInfo(client,"HANDLER CMD S2C :");
            new HandlerReadClientInfo(client);
        }

//        if (!flag) {
//            new HandlerReadCMDInfo(client,"HANDLER CMD S2C :");
//            new HandlerReadClientInfo(client);
//        }
    }
}

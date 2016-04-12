package com.dongfang.server;

import com.dongfang.bean.BaseBean;
import com.dongfang.bean.SocketMsgBean;
import com.google.gson.Gson;
import rx.Observable;
import rx.functions.Func1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
            new HandlerReadCMDInfo(client, "HANDLER CMD S2C :");
            new HandlerReadClientInfo(client);
            init();
            Observable.just(client)
                    .flatMap(new Func1<Socket, Observable<?>>() {
                        public Observable<?> call(Socket socket) {
                            try {
                                outToClient = null == outToClient ? new PrintStream(socket.getOutputStream()) : outToClient;
                                mBean.id = UI_ID + count++;
                                mSocketMegBean.msgId = mBean.id;
                                mBean.data = new Gson().toJson(mSocketMegBean);
                                String str = new Gson().toJson(mBean);
                                System.out.println("To JSON --> " + str);
                                outToClient.println(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return Observable.just(socket);
                        }
                    })
                    .delay(3, TimeUnit.SECONDS)
                    .repeat()
                    .subscribe();


        }

//        if (!flag) {
//            new HandlerReadCMDInfo(client,"HANDLER CMD S2C :");
//            new HandlerReadClientInfo(client);
//        }
    }

    static PrintStream outToClient;

    static final long UI_ID = 999000;
    static BaseBean mBean;
    static SocketMsgBean mSocketMegBean;

    static int count = 0;


    public static void init() {
        mBean = new BaseBean();
        mBean.id = UI_ID;
        mBean.msg = "ok";
        mBean.data = "heart";

        mSocketMegBean = new SocketMsgBean();
        mSocketMegBean.msgId = UI_ID;
        mSocketMegBean.mstType = SocketMsgBean.MSG_TYPE_SOCKET;
    }


}

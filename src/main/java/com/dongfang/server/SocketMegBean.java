package com.dongfang.server;

import java.util.Arrays;

/**
 * Created by dongfang on 2016/3/31.
 */
public class SocketMegBean {
    public static final int MSG_TYPE_HTTP = 0;
    public static final int MSG_TYPE_SOCKET = 1;

    public String id;
    public int mstType;
    public String msg;
    public String data;
    public String[] dataArrary;


    @Override
    public String toString() {
        return "SocketMegBean{" +
                "id='" + id + '\'' +
                ", msgType='" + mstType + '\'' +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", dataArrary=" + Arrays.toString(dataArrary) +
                '}';
    }
}

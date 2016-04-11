package com.dongfang.bean;

import java.util.Arrays;

/**
 * Created by dongfang on 2016/3/31.
 */
public class SocketMsgBean {
    public static final int MSG_TYPE_HTTP = 0;
    public static final int MSG_TYPE_SOCKET = 1;

    public long msgId;
    public int mstType;
    public String[] dataArrary;


    @Override
    public String toString() {
        return "SocketMsgBean{" +
                "msgType='" + mstType + '\'' +
                ", dataArrary=" + Arrays.toString(dataArrary) +
                '}';
    }
}

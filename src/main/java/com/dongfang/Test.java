package com.dongfang;

import com.dongfang.bean.BaseBean;
import com.dongfang.bean.HeartMsgBean;
import com.google.gson.Gson;

/**
 * Created by dongfang on 16/4/9.
 */
public class Test {

    public static void main(String[] args) {
//        String str = "GET /LANG_H?token=88888888?num=111&nonce=10292837465&c=87654&sig=83337654&cofig=10000 HTTP/1.1";
//        String l = str.substring(str.indexOf("num=") + 4, str.indexOf("&"));
//        System.out.println(l);


//        SocketMsgBean msgBean = new SocketMsgBean();
//        msgBean.mstType = SocketMsgBean.MSG_TYPE_SOCKET;
//        msgBean.dataArrary = new String[]{"qq", "ww"};
//
//        BaseBean<SocketMsgBean> bean = new BaseBean<SocketMsgBean>();
//        bean.id = 1000;
//        bean.msg = "OK";
//        bean.data = new Gson().toJson(msgBean);
//
//
//        System.out.println(new Gson().toJson(bean));


//        String s = "{\"id\":777002,\"msg\":\"ok\",\"data\":\"{\\\"msgId\\\":777002,\\\"mstType\\\":1,\\\"dataArrary\\\":[\\\"1\\\",\\\"2\\\",\\\"3\\\",\\\"4\\\"]}\"}";
//
//        System.out.println(new Gson().fromJson(s,BaseBean.class));

//        System.out.println(lastRemaining(1,3));
//        System.out.println(lastRemaining(2,3));
//        System.out.println(lastRemaining(3,3));
//        System.out.println(lastRemaining(4,3));
//        System.out.println(lastRemaining(5,3));
//        System.out.println(lastRemaining(6,3));
//        System.out.println(lastRemaining(7,3));
//        System.out.println(lastRemaining(8,3));


        System.out.println(HeartMsgBean.class.isAssignableFrom(BaseBean.class));
        System.out.println(BaseBean.class.isAssignableFrom(HeartMsgBean.class));

    }







    public static int lastRemaining(int n, int m){
        if(n < 1 || m < 1){
            return -1;
        }
        int last = 0;
        for(int i = 2; i <= n; i++){
            last = (last + m) % i;
        }
        return last+1;
    }
}

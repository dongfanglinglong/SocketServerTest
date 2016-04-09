package com.dongfang;

/**
 * Created by dongfang on 16/4/9.
 */
public class Test {

    public static void main(String[] args) {
        String str = "GET /LANG_H?token=88888888?num=111&nonce=10292837465&c=87654&sig=83337654&cofig=10000 HTTP/1.1";
        String l = str.substring(str.indexOf("num=") + 4, str.indexOf("&"));
        System.out.println(l);

    }
}

package com.dongfang.client;

import com.dongfang.server.HandlerReadCMDInfo;

import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by dongfang on 2016/3/30.
 */
public class Client {

    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        final Socket client = new Socket("10.128.4.4", 9451);
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                client.getOutputStream(), "UTF-8")));
        //客户端请求与本机在20011端口建立TCP连接
        client.setSoTimeout(3600 * 1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    Map<String, String> paramMap = new HashMap<String, String>();
                    paramMap.put("action", "heartbeat");

                    paramMap.put("userType", "1");//表示用户类型，为将来扩展预留。默认第一期只有一种类型: B端用户，值为1
                    paramMap.put("userRole", "1");//用户业务角色，比如内勤，外勤等

                    paramMap.put("orderId", "22222");//订单id
                    paramMap.put("city", "31010");//城市id
                    paramMap.put("lng", "123.44346");//经度
                    paramMap.put("lat", "33.243745");//纬度
                    paramMap.put("id", "1234567890");
//                    paramMap.put("token", "1234567890");
                    sendRequest(paramMap, "cometHeartbeat");
//
//
//                    Map<String, String> paramMap = new HashMap<String, String>();
//                    paramMap = new HashMap<String, String>();
//                    paramMap.put("action", "connect");
//                    paramMap.put("appVersion", "7.1");
//                    paramMap.put("id", "1234567890");
//                    paramMap.put("appVersion", "11111");
//                    paramMap.put("osType", "1");
//                    paramMap.put("iosToken", "e9c0dac6794a035086ca3fecdada5c331637554b1ecc612654669dbba7d7fd4c");
//                    paramMap.put("udId", "DB390EB7-DE75-46DC-A423-DC08B2075D68");
//                    paramMap.put("osVersion", "1.0.0.10");
//                    paramMap.put("city", "12");
//                    sendRequest(paramMap, "CustomerAction");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new HandlerReadServerInfo(client);
        new HandlerReadCMDInfo(client, "HANDLER CMD C2S:");


//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        //获取键盘输入
//        PrintStream out = new PrintStream(client.getOutputStream());
//        //获取Socket的输出流，用来发送数据到服务端
//        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        //获取Socket的输入流，用来接收从服务端发送过来的数据
//        boolean flag = true;
//        while (flag) {
//            System.out.print("输入信息：");
//            String str = input.readLine();
//            out.println(str);
//            //发送数据到服务端
//            if ("bye".equals(str)) {
//                flag = false;
//            } else {
//                try {
//                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
//                    String echo = buf.readLine();
//                    System.out.println(echo);
//                } catch (SocketTimeoutException e) {
//                    System.out.println("Time out, No response");
//                }
//            }
//        }
//        input.close();
//        if (client != null) {
//            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
//            client.close(); //只关闭socket，其关联的输入输出流也会被关闭
//        }
    }

    private static void sendRequest(Map<String, String> paramMap, String action) {
        paramMap.put("token", "1234567890");
        paramMap.put("nonce", String.valueOf(System.currentTimeMillis()));
        String uri = "/" + action + "?" + buildParamURL(paramMap, "");
        // System.out.println("------->" + uri);
        StringBuilder sb = new StringBuilder("GET " + uri + " HTTP/1.1\r\n");
        sb.append("User-Agent: Java/1.6.0_20\r\n");
        sb.append("Host: 127.0.0.1:9000\r\n");
        sb.append("Accept: text/json\r\n");
        sb.append("Connection: keepalive\r\n");
        sb.append("\r\n");

        System.out.println(sb.toString());
        pw.write(sb.toString());
        pw.flush();
    }


    public static String buildParamURL(Map<String, String> paramMap, String secret) {
        if (paramMap != null && paramMap.size() > 0) {
            try {
                List<String> list = new ArrayList<String>();
                for (String paramName : paramMap.keySet()) {
                    if (!paramName.equals("sig")) {
                        list.add(paramName);
                    }
                }
                Collections.sort(list);
                StringBuffer sb = new StringBuffer();
                // sb.append(secret);
                for (String paramName : list) {
                    sb.append("&" + paramName + "=");
                    sb.append(paramMap.get(paramName));
                }
                //String paramsSign = Base64.encodeBytes(DigestUtils.md5Hex(sb.toString()).getBytes(CometConstant.DEFAULT_CHARSET));
                //paramMap.put("sig", paramsSign);
                sb = new StringBuffer();
                for (String key : paramMap.keySet()) {
                    sb.append(key + "=" + URLEncoder.encode(paramMap.get(key), "UTF-8") + "&");
                }
                return sb.substring(0, sb.length() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

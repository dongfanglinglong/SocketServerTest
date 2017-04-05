package com;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Auth dongfang
 * Date 2017/3/13
 */
public class JTest {


    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("1", "11");
        map.put("2", "21");
        map.put("3", "31");
        map.put("4", "41");
        map.put("5", "51");


//        for (String key : map.keySet()) {
//            if ("3".equals(key)) {
//                map.remove(key);
//            }
//        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(key.equals("3") || "2".equals(key)){
                iterator.remove();
            }
        }



        for (String key : map.keySet()) {
            System.out.println(key + ":[" + map.get(key) + "]");
        }


    }
}

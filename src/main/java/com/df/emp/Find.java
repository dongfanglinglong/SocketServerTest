package com.df.emp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;

/**
 * Auth dongfang
 * Date 2017/3/17
 */
public class Find {


    public Find() {
    }

    public String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public void find() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(path));
            String line = read.readLine();
//            while ((line = read.readLine()) != null) {
//                if (!line.equals("")) {
//                    // System.out.println(line);
//                    sb.append(line);
//                }
//            }
            read.close();
            if (line.contains("质量部(海浪QA)")) {
                System.out.println(line);
            }
            line = null;

        } catch (Throwable e) {
            // System.out.println(e);
        }
    }

    public static void main(String[] args) {


        Find find = new Find();
        DecimalFormat df = new DecimalFormat("00000");
        String path = "/Users/dongfang/Documents/didi_id/D";

        String filePath;
        for (int i = 1; i < 11000; i++) {
            filePath = path + df.format(i) + ".json";
            find.setPath(filePath);
            find.find();
        }
    }

}

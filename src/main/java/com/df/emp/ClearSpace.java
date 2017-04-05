package com.df.emp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Auth dongfang
 * Date 2017/3/17
 */
public class ClearSpace {


    public String path;

    public ClearSpace(String path) {
        this.path = path;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public void clear() {
        try {
            StringBuilder sb = new StringBuilder();

            BufferedReader read = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = read.readLine()) != null) {
                if (!line.equals("")) {
                    System.out.println(line);
                    sb.append(line);
                }
            }
            read.close();


            line = sb.toString();

            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(line);
            line = m.replaceAll("");


            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(line);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {


        ClearSpace clearSpace = new ClearSpace("/Users/dongfang/Documents/emp/D00001.json");
        DecimalFormat df = new DecimalFormat("00000");
        String path = "/Users/dongfang/Documents/emp/D";

        String filePath ;
        for (int i = 1; i < 11000; i++) {
            filePath = path + df.format(i) + ".json";
            clearSpace.setPath(filePath);
            clearSpace.clear();
        }
    }


}

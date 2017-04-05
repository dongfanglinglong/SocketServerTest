package com.df.emp;

import com.google.gson.Gson;

import java.io.*;
import java.text.DecimalFormat;

/**
 * Auth dongfang
 * Date 2017/3/17
 */
public class NameId {


    public NameId() {
    }

    public String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Base writerNameId() {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader read = new BufferedReader(new FileReader(path));
            Base aaa = new Gson().fromJson(read, Base.class);
            System.out.println(aaa.base_info);
            return aaa;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    class Base {
        public BaseInfo base_info;
    }

    public static void main(String[] args) {
        try {
            ClearSpace clearSpace = new ClearSpace("/Users/dongfang/Documents/didi_id/D00001.json");
            DecimalFormat df = new DecimalFormat("00000");
            String path = "/Users/dongfang/Documents/didi_id/D";

            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/dongfang/Documents/didi_id/D00000.txt"));
            NameId nameId = new NameId();

            String filePath;
            String line = "";

            Base base;

            for (int i = 1; i < 11000; i++) {
                filePath = path + df.format(i) + ".json";
                nameId.setPath(filePath);
                base = nameId.writerNameId();

                if (null != base && null != base.base_info) {
                    writer.write(base.base_info.EMPLOYEE_ID
                            + " " + base.base_info.name
                            + (null != base.base_info.name && base.base_info.name.length() > 2 ? "  " : "    ")
                            + base.base_info.SUPERVISOR_NAME
                            + "\n");
                    writer.flush();
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

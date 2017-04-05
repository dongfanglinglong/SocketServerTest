package com.df.emp;

/**
 * Auth dongfang
 * Date 2017/3/17
 */

public class BaseInfo {

    public String EMPLOYEE_ID;
    public String name;
    public String SUPERVISOR_NAME;


    @Override
    public String toString() {
        return "BaseInfo{" +
                "EMPLOYEE_ID='" + EMPLOYEE_ID + '\'' +
                ", name='" + name + '\'' +
                ", SUPERVISOR_NAME='" + SUPERVISOR_NAME + '\'' +
                '}';
    }
}

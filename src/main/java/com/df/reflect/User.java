package com.df.reflect;

/**
 * Auth dongfang
 * Date 2017/3/30
 */
public class User {

    String name;
    int id;


    public User() {
        name = "123";
        id = 123;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

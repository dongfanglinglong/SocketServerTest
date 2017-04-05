package com.df.reflect;

import java.lang.reflect.Field;

/**
 * Auth dongfang
 * Date 2017/3/30
 */
public class ReflectTest {


    public static void main(String[] args) {

        User user = new User("787654", 87);

        try {
            Field field = user.getClass().getDeclaredField("name");
            System.out.println("001: " + field);

            for (Field f : user.getClass().getDeclaredFields()) {
                System.out.println("-- : " + f);
            }


            System.out.println(field.get(user));
            field.set(user,"new");
            System.out.println(user.getName());


        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        Field[] fs = User.class.getFields();
//        for (Field f : fs) {
//            System.out.println("------"+f.getName());
//        }


    }

}

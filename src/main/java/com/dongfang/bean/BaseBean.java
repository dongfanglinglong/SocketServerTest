package com.dongfang.bean;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by dongfang on 2016/4/7.
 */
public class BaseBean<T> {
    public long id;
    public String msg;
    public String data;


    public T fromJson() {
        Type[] parameterizedTypes = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        Class pClazz = (Class) parameterizedTypes[0];
        return (T) new Gson().fromJson(data, pClazz);
    }


    public BaseBean() {
    }


    @Override
    public String toString() {
        return "BaseBean{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

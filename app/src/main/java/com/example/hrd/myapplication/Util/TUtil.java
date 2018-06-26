package com.example.hrd.myapplication.Util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Jacky on 2017/5/25.
 */

public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  Type getType(Object o, int i) {
        try {
            return  ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i];
        }  catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  Type getSecondType(Object o, int i) {
        try {
            return  ((ParameterizedType)((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i].getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }  catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}

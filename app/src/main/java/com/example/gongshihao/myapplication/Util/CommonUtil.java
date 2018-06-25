package com.example.gongshihao.myapplication.Util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

public class CommonUtil {

    public static class  UserData{
        public final static String USER_SP="hospital";
        public final static String UserName="UserName";
        public final static String NetWork="NetWork";
    }


    public static boolean CheckNotNull(String str){

        return str!=null&&!str.equals("");
    }
}

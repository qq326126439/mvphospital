package com.example.hrd.myapplication.Util;

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
    public static class StoreData{
        public final static String STORECODE="StoreCode";
        public final static String STORENAME="StoreName";
    }
    public static class Department{
        public final static String DEPARTMENTNAME="DepartmentName";
        public final static String DEPARTMENTCODE="DepartmentCode";
    }
    public static class Equip{
        public final static String STORENAME="StoreName";
        public final static String EQUIPNAME="EquipName";
        public final static String MODEL="MODEL";
        public final static String DEPARTMENT="Department";
        public final static String VALUE="Value";
        public final static String EQUIPID="EquipId";
    }


    public static boolean CheckNotNull(String str){

        return str!=null&&!str.equals("");
    }
}

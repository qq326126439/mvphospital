package com.example.hrd.myapplication.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
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
        public final static String SIGNDATA="signdata";
    }


    public static boolean CheckNotNull(String str){

        return str!=null&&!str.trim().equals("");
    }

    public static void showdialog(int Dtype,Context context,String msg,String btnName)
    {
        String[] btnNames=btnName.split(",");
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(context);
        alertBuilder.setMessage(msg);
        if(Dtype==1)
        {
            alertBuilder.setPositiveButton(btnNames[0], new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert=alertBuilder.create();
            alert.show();
        }
    }


}

package com.example.hrd.myapplication.Util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.Toast;

import com.example.hrd.myapplication.MyApplication;
import com.example.hrd.myapplication.R;
import com.hss01248.dialog.StyledDialog;

/**
 * Created by Jacky on 2017/5/27.
 */

public class ViewUtil {


    /***
     * 封装recycler基本属性
     * @param context
     * @param recyclerView
     * @param orientation listivew列表传1或者LinearLayoutManager.VERTICAL，grid列表传固定值
     */
    public static RecyclerView.LayoutManager initRecyclerViewStyle(Context context, RecyclerView recyclerView, int orientation) {
        RecyclerView.LayoutManager layoutManager;
        /**提高性能*/
        recyclerView.setHasFixedSize(true);

        if (LinearLayoutManager.HORIZONTAL == orientation || LinearLayoutManager.VERTICAL == orientation) {
            /**listview*/
            layoutManager = new LinearLayoutManager(context);
            ((LinearLayoutManager) layoutManager).setOrientation(orientation);
        } else {
            /**gridview**/
            layoutManager = new GridLayoutManager(context, orientation);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        return layoutManager;
    }


    private static Toast toast;
    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT); //创建Toast对象
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
        }
        toast.show();
    }



}

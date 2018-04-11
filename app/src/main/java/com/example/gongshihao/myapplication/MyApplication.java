package com.example.gongshihao.myapplication;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by gongshihao on 2018/4/11.
 */

public class MyApplication extends MultiDexApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        context=getApplicationContext();
        Fragmentation.builder().stackViewMode(Fragmentation.NONE).debug(BuildConfig.DEBUG).install();
    }

    public static Context getContext() {
        return context;
    }


}

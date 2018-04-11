package com.example.gongshihao.myapplication.ImageUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;

/**
 * Created by gongshihao on 2018/3/28.
 */

public class GlideUtil {
    public static <T> void LoadCommonImage(Context context,T Resource,ImageView imageView){
        Glide.with(context).load(Resource).into(imageView);
    }
    public static <T> void LoadCircleImage(Context context,T Resource,ImageView imageView){
        RequestOptions options=new RequestOptions().circleCrop();
        Glide.with(context).load(Resource).apply(options).into(imageView);
    }
    public static <T> void PrepareCommonImage(Context context,T Resource,ImageView imageView){
        Glide.with(context).load(Resource).preload();
    }
    public static <T> void LoadImage(Context context,T Resource,ImageView imageView,RequestOptions requestOptions){
        Glide.with(context).load(Resource).apply(requestOptions).into(imageView);
    }



}

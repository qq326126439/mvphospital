package com.example.hrd.myapplication.http;

import android.content.Intent;
import android.util.Log;

import com.example.hrd.myapplication.Util.TUtil;
import com.example.hrd.myapplication.bean.StoreListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import ikidou.reflect.TypeBuilder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserve <R> implements Observer<Webservice<String>> {

    Disposable disposable;
    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }



    public abstract void onSuccess(R t);
    public abstract void onSuccess(String msg);
    public abstract void onFailure(String s);

    @Override
    public void onNext(Webservice storeListBean) {
        if(storeListBean!=null&&Integer.valueOf(storeListBean.getState())!=-1){
            String json= (String) storeListBean.getData();
            Type BaseType=TUtil.getType(this,0);
            R r=new Gson().fromJson(json,BaseType);
            if(storeListBean.getData()!=null){
                onSuccess(r);
            }else{
                onSuccess(storeListBean.getMessage());
            }

        }else{
            onFailure(storeListBean.getMessage());
        }

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e.getMessage());
        disposable.dispose();
    }
    @Override
    public void onComplete() {

    }
}

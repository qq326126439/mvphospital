package com.example.gongshihao.myapplication.Util;

import android.icu.lang.UProperty;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by gongshihao on 2018/4/20.
 */

public class RxthrottleFirst <T> {

    public  static <T> ObservableTransformer<T,T> applyThrottleFirst(){
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.throttleFirst(1, TimeUnit.MICROSECONDS);
            }
        };
    }
}

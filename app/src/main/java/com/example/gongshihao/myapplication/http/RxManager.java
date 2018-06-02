package com.example.gongshihao.myapplication.http;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jacky on 2017/6/28.
 */

public class RxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add (Disposable m){
        compositeDisposable.add(m);
    }

    public void clear() {
        if(compositeDisposable != null)
            compositeDisposable.clear();
    }
}

package com.example.gongshihao.myapplication.mvp;



/**
 * Created by Jacky on 2017/5/25.
 */

public abstract  class BasePresenter <M, T> {

    public M mModel;
    public T mView;
//    public RxManager rx = new RxManager();

    public void attachVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();

    }

    public void detachVM() {
        mView = null;
        mModel = null;
//        rx.clear();
    }




    public abstract void onStart();

}

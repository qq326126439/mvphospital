package com.example.hrd.myapplication.ui.fragment.StoreConstracts;


import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.bean.StoreBean;
import com.example.hrd.myapplication.bean.StoreListBean;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

public class StoreModel implements StoreConstracts.StoreModel{
    @Override
    public Observable<StoreListBean<String>> getList() {
        return Observable.create(new ObservableOnSubscribe<StoreListBean<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<StoreListBean<String>> e) throws Exception {
                XWebService.getIntentData(StoreListBean.class, "getStorageRoomList", null, new XWebService.OnResultListener<StoreListBean>() {
                    @Override
                    public void onSuccess(StoreListBean bean) {
                        e.onNext(bean);
                        e.onComplete();
                    }

                    @Override
                    public void onError(Exception exception) {
                        e.onError(exception);
                    }
                });

            }
        });
    }
}

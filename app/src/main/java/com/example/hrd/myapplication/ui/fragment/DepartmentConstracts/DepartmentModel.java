package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;

import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.Webservice;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DepartmentModel implements DepartmentConstracts.DepartmentModel{
    @Override
    public Observable<Webservice<String>> getList(final String code) {
        return Observable.create(new ObservableOnSubscribe<Webservice<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<Webservice<String>> e) throws Exception {
                SimpleArrayMap simpleArrayMap=new SimpleArrayMap();
                simpleArrayMap.put("arg0",code);
                XWebService.getIntentData(Webservice.class, "getDeptListByStorageRoomCode", simpleArrayMap, new XWebService.OnResultListener<Webservice>() {
                    @Override
                    public void onSuccess(Webservice bean) {
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

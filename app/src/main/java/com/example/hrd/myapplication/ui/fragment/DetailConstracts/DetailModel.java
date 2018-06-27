package com.example.hrd.myapplication.ui.fragment.DetailConstracts;

import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.Webservice;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DetailModel implements DetailConstracts.DetailModel{

    @Override
    public Observable<Webservice<String>> getList(final String StoreId, final String DepartmentId, final String type) {
        return Observable.create(new ObservableOnSubscribe<Webservice<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<Webservice<String>> e) throws Exception {
                SimpleArrayMap args=new SimpleArrayMap();
                args.put("arg0",StoreId);
                args.put("arg1",DepartmentId);
                args.put("arg2",type);
                XWebService.getIntentData(Webservice.class, "getEquipmentListByDeptIdAndStorageRoomCode", args, new XWebService.OnResultListener<Webservice>() {
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

package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;

import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.Webservice;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class PropertyModel implements PropertyConstracts.PropertyModel{
    @Override
    public Observable<Webservice> PostComment(final String userName, final String EquipNum, final String Comment, final String type) {
        return Observable.create(new ObservableOnSubscribe<Webservice>() {
            @Override
            public void subscribe(final ObservableEmitter<Webservice> e) throws Exception {
                SimpleArrayMap args=new SimpleArrayMap();
                args.put("args0",EquipNum);
                args.put("args1",userName);
                args.put("args2",type);
                args.put("args3",Comment);
                XWebService.getIntentData(Webservice.class, "insertInventory", args, new XWebService.OnResultListener<Webservice>() {
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

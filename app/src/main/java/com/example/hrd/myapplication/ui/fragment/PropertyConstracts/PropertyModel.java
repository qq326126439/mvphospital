package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;

import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.Webservice;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class PropertyModel implements PropertyConstracts.PropertyModel{
    @Override
    public Observable<Webservice<String>> PostComment(final String userName, final String EquipNum, final String Comment, final String type) {
        return Observable.create(new ObservableOnSubscribe<Webservice<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<Webservice<String>> e) throws Exception {
                SimpleArrayMap args=new SimpleArrayMap();
                args.put("arg0",EquipNum);
                args.put("arg1",userName);
                args.put("arg2",type);
                args.put("arg3",Comment);
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

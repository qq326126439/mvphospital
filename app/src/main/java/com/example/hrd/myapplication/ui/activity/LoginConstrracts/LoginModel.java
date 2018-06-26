package com.example.hrd.myapplication.ui.activity.LoginConstrracts;
import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LoginModel implements LoginConstracts.LoginModel{

    @Override
    public Observable<String> Login(String url) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                XWebService.TestConnect("getStorageRoomList", new XWebService.OnResultListener<String>() {
                    @Override
                    public void onSuccess(String bean) {
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

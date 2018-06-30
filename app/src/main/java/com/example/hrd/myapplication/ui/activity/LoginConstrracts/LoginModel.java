package com.example.hrd.myapplication.ui.activity.LoginConstrracts;
import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.WebService.WebServiceUtil;
import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

public class LoginModel implements LoginConstracts.LoginModel{

    @Override
    public Observable<String> Login(String url) {
        return WebServiceUtil.post()
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .methodName("getStorageRoomList")
                .build()
                .execute().map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Webservice fromJson=new Gson().fromJson(s, new TypeToken<Webservice>(){}.getType());
                        return fromJson.getMessage();
                    }
                }).compose(RxSchedulers.<String>applySchedulers());
    }
}

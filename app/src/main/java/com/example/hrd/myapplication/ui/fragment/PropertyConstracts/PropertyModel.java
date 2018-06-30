package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;


import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.WebService.WebServiceUtil;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class PropertyModel implements PropertyConstracts.PropertyModel{
    @Override
    public Observable<Webservice<String>> PostComment(final String userName, final String EquipNum, final String Comment, final String type) {
        return WebServiceUtil
                .post()
                .addParams("arg0",EquipNum)
                .addParams("arg1",userName)
                .addParams("arg2",type)
                .addParams("arg3",Comment)
                .methodName("insertInventory")
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .build().execute().map(new Function<String, Webservice<String>>() {
                    @Override
                    public Webservice<String> apply(String s) throws Exception {
                        Webservice o=new Gson().fromJson(s,new TypeToken<Webservice<String>>(){}.getType());
                        return o;
                    }
                }).compose(RxSchedulers.<Webservice<String>>applySchedulers());
    }
}

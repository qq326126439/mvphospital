package com.example.hrd.myapplication.ui.fragment.StoreConstracts;



import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.WebService.WebServiceUtil;
import com.example.hrd.myapplication.bean.StoreBean;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class StoreModel implements StoreConstracts.StoreModel{
    @Override
    public Observable<Webservice<List<StoreBean>>> getList() {
        return WebServiceUtil
                .post()
                .methodName("getStorageRoomList")
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .build()
                .execute()
                .map(new Function<String, Webservice<List<StoreBean>>>() {
                    @Override
                    public Webservice<List<StoreBean>> apply(String s) throws Exception {
                        Webservice fromJson=new Gson().fromJson(s,new TypeToken<Webservice>(){}.getType());
                        List<StoreBean> mlist=new Gson().fromJson(fromJson.getData().toString(),new TypeToken<List<StoreBean>>(){}.getType());
                        fromJson.setData(mlist);
                        return fromJson;
                    }
                }).compose(RxSchedulers.<Webservice<List<StoreBean>>>applySchedulers());
    }
}

package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;


import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.WebService.WebServiceUtil;
import com.example.hrd.myapplication.bean.DepartmentBean;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DepartmentModel implements DepartmentConstracts.DepartmentModel{
    @Override
    public Observable<Webservice<List<DepartmentBean>>> getList(String code) {
        return WebServiceUtil.post()
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .methodName("getDeptListByStorageRoomCode")
                .addParams("arg0",code)
                .build()
                .execute().map(new Function<String, Webservice<List<DepartmentBean>>>() {
                    @Override
                    public Webservice<List<DepartmentBean>> apply(String s) throws Exception {
                        Webservice fromJson=new Gson().fromJson(s,new TypeToken<Webservice>(){}.getType());
                        fromJson.setData(new Gson().fromJson(fromJson.getData().toString(),new TypeToken<List<DepartmentBean>>(){}.getType()));
                        return fromJson;
                    }
                }).compose(RxSchedulers.<Webservice<List<DepartmentBean>>>applySchedulers());
    }
}

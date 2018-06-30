package com.example.hrd.myapplication.ui.fragment.DetailConstracts;

import android.support.v4.util.SimpleArrayMap;

import com.example.hrd.myapplication.Constracts.NetworkConstracts;
import com.example.hrd.myapplication.WebService.WebServiceUtil;
import com.example.hrd.myapplication.WebService.XWebService;
import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.http.RxSchedulers;
import com.example.hrd.myapplication.http.Webservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

public class DetailModel implements DetailConstracts.DetailModel{

    @Override
    public Observable<Webservice<List<EquipmentBean>>> getList(String searchKey, String StoreId, String DepartmentId, String type, String nowPage) {
        return WebServiceUtil
                .post()
                .addParams("arg0",searchKey)
                .addParams("arg1",StoreId)
                .addParams("arg2",DepartmentId)
                .addParams("arg3",type)
                .addParams("arg4",nowPage)
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .methodName("getEquipmentListByDeptIdAndStorageRoomCode")
                .build().execute()
                .map(new Function<String, Webservice<List<EquipmentBean>>>() {
                    @Override
                    public Webservice<List<EquipmentBean>> apply(String s) throws Exception {
                        Webservice fromJson=new Gson().fromJson(s,new TypeToken<Webservice>(){}.getType());
                        fromJson.setData(new Gson().fromJson(fromJson.getData().toString(),new TypeToken<List<EquipmentBean>>(){}.getType()));
                        return fromJson;
                    }
                }).compose(RxSchedulers.<Webservice<List<EquipmentBean>>>applySchedulers());
    }

    @Override
    public Observable<Webservice<EquipmentBean>> SearchById(String getEquipmentDetByEquipmentNum) {
        return WebServiceUtil
                .post()
                .addParams("arg0",getEquipmentDetByEquipmentNum)
                .namespace(NetworkConstracts.WebServiceNameSpace)
                .methodName("getEquipmentDetByEquipmentNum")
                .build()
                .execute().map(new Function<String, Webservice<EquipmentBean>>() {
                    @Override
                    public Webservice<EquipmentBean> apply(String s) throws Exception {
                        Webservice o=new Gson().fromJson(s,new TypeToken<Webservice>(){}.getType());
                        o.setData(new Gson().fromJson(s,new TypeToken<EquipmentBean>(){}.getType()));
                        return o;
                    }
                }).compose(RxSchedulers.<Webservice<EquipmentBean>>applySchedulers());
    }
}

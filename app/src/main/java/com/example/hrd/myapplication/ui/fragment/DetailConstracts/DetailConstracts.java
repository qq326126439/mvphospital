package com.example.hrd.myapplication.ui.fragment.DetailConstracts;

import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.http.Webservice;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import java.util.List;

import io.reactivex.Observable;


public interface DetailConstracts {
     abstract class DetailPresenter extends BasePresenter<DetailModel,DetailMview>{
        public abstract void getList(String StoreId,String DepartmentId,String type);
    }
    interface DetailModel extends BaseModel{
        Observable<Webservice<String>> getList(String StoreId,String DepartmentId,String type);
    }
    interface DetailMview extends BaseView{
        void Updatea(List<EquipmentBean> mlist);
    }
}

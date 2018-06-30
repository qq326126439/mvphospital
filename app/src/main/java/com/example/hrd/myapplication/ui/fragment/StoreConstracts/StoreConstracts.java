package com.example.hrd.myapplication.ui.fragment.StoreConstracts;

import com.example.hrd.myapplication.bean.StoreBean;
import com.example.hrd.myapplication.bean.StoreListBean;
import com.example.hrd.myapplication.http.Webservice;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import java.util.List;

import io.reactivex.Observable;

public interface StoreConstracts {
    abstract class StorePresenter extends BasePresenter<StoreModel,StoreMview>{
        public abstract void getList();
    }
    interface StoreModel extends BaseModel{
        Observable<Webservice<List<StoreBean>>> getList();
    }
    interface StoreMview extends BaseView{
        void UpdateView(List<StoreBean> list);
    }
}

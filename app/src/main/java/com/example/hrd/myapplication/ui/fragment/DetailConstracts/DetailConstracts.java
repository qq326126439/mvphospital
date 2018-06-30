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
        public abstract void getList(String searchKey,String StoreId,String DepartmentId,String type,String nowPage);
         public abstract void SearchById(String getEquipmentDetByEquipmentNum);
    }
    interface DetailModel extends BaseModel{
        Observable<Webservice<List<EquipmentBean>>> getList(String searchKey,String StoreId,String DepartmentId,String type,String nowPage);
        Observable<Webservice<EquipmentBean>> SearchById(String getEquipmentDetByEquipmentNum);

    }
    interface DetailMview extends BaseView{
        void Updatea(List<EquipmentBean> mlist);
        void Goto(EquipmentBean bean);
    }
}

package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;

import com.example.hrd.myapplication.bean.DepartmentBean;
import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.http.Webservice;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import java.util.List;

import io.reactivex.Observable;

public interface DepartmentConstracts {
     abstract class DepartmentPresenter extends BasePresenter<DepartmentModel,DepartmentMview>{
        public abstract void getList(String code);
    }
    interface DepartmentModel extends BaseModel{
        Observable<Webservice<String>> getList(String code);

    }

    interface DepartmentMview extends BaseView{
        void UpdataView(List<DepartmentBean> mlist);
    }

}

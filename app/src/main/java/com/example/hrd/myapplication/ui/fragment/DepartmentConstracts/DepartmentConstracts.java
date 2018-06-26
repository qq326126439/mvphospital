package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;

import com.example.hrd.myapplication.http.Webservice;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import io.reactivex.Observable;

public interface DepartmentConstracts {
     abstract class DepartmentPresenter extends BasePresenter<DepartmentModel,DepartmentMview>{

    }
    interface DepartmentModel extends BaseModel{
        Observable<Webservice<String>> getList(String code);

    }

    interface DepartmentMview extends BaseView{

    }

}

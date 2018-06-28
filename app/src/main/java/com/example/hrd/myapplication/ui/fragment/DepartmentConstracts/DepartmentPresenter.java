package com.example.hrd.myapplication.ui.fragment.DepartmentConstracts;


import com.example.hrd.myapplication.bean.DepartmentBean;
import com.example.hrd.myapplication.http.BaseObserve;
import com.example.hrd.myapplication.http.Webservice;

import java.util.List;

public class DepartmentPresenter extends DepartmentConstracts.DepartmentPresenter{
    @Override
    public void onStart() {

    }

    @Override
    public void getList(String code) {
        mModel.getList(code).subscribe(new BaseObserve<List<DepartmentBean>>() {
            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onSuccess(List<DepartmentBean> t) {
                mView.UpdataView(t);
            }

            @Override
            public void onFailure(String s) {
                mView.showError(s);
            }
        });
    }
}

package com.example.hrd.myapplication.ui.fragment.DetailConstracts;

import android.support.v7.view.menu.MenuView;

import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.http.BaseObserve;

import java.util.List;

public class DetailPresenter extends DetailConstracts.DetailPresenter{
    @Override
    public void onStart() {

    }


    @Override
    public void getList(String StoreId, String DepartmentId,String type) {
        mModel.getList(StoreId,DepartmentId,type).subscribe(new BaseObserve<List<EquipmentBean>>() {
            @Override
            public void onSuccess(List<EquipmentBean> t) {
                mView.Updatea(t);
            }

            @Override
            public void onFailure(String s) {
                mView.showError(s);
            }
        });
    }
}

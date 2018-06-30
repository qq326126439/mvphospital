package com.example.hrd.myapplication.ui.fragment.DetailConstracts;

import com.example.hrd.myapplication.bean.EquipmentBean;
import com.example.hrd.myapplication.http.BaseObserve;

import java.util.List;

public class DetailPresenter extends DetailConstracts.DetailPresenter{
    @Override
    public void onStart() {

    }

    @Override
    public void SearchById(String getEquipmentDetByEquipmentNum) {
        mModel.SearchById(getEquipmentDetByEquipmentNum).subscribe(new BaseObserve<EquipmentBean>() {
            @Override
            public void onSuccess(EquipmentBean equipmentBean) {
                mView.Goto(equipmentBean);
            }

            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onFailure(String s) {
                mView.showError(s);
            }
        });
    }

    @Override
    public void getList(String searchKey,String StoreId, String DepartmentId,String type,String page) {
        mModel.getList(searchKey,StoreId,DepartmentId,type,page).subscribe(new BaseObserve<List<EquipmentBean>>() {
            @Override
            public void onSuccess(List<EquipmentBean> equipmentBeans) {
                mView.Updatea(equipmentBeans);
            }

            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onFailure(String s) {
                mView.showError(s);
            }
        });
    }
}

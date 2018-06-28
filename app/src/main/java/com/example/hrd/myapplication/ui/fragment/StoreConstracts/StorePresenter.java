package com.example.hrd.myapplication.ui.fragment.StoreConstracts;

import com.example.hrd.myapplication.bean.StoreBean;
import com.example.hrd.myapplication.http.BaseObserve;
import java.util.List;
public class StorePresenter extends StoreConstracts.StorePresenter{
    @Override
    public void onStart() {

    }

    @Override
    public void getList() {
        mModel.getList().subscribe(new BaseObserve<List<StoreBean>>() {
            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onSuccess(List<StoreBean> t) {
                mView.UpdateView(t);
            }

            @Override
            public void onFailure(String s) {
                mView.showError(s);
            }
        });
    }
}

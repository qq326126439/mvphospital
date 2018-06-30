package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;

import com.example.hrd.myapplication.http.BaseObserve;

public class PropertyPresenter extends PropertyConstracts.PropertyPresenter{
    @Override
    public void PostComment(String userName, String EquipNum, String Comment, String type) {
        mModel.PostComment(userName,EquipNum,Comment,type).subscribe(new BaseObserve<String>() {
            @Override
            public void onSuccess(String t) {
                mView.ShowDialog(t);
//                mView.backPre();//继承返回上一层的方法
            }

            @Override
            public void onFailure(String s)
            {
                mView.showError(s);
            }
        });
    }

    @Override
    public void onStart() {

    }
}

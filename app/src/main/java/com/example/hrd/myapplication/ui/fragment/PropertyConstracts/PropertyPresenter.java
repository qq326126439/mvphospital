package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;

public class PropertyPresenter extends PropertyConstracts.PropertyPresenter{
    @Override
    public void PostComment(String userName, String EquipNum, String Comment, String type) {
        mModel.PostComment(userName,EquipNum,Comment,type);
    }

    @Override
    public void onStart() {

    }
}

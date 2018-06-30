package com.example.hrd.myapplication.ui.fragment.PropertyConstracts;

import com.example.hrd.myapplication.http.Webservice;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import io.reactivex.Observable;

public interface PropertyConstracts {
    abstract class PropertyPresenter extends BasePresenter<PropertyModel,PropertyMview>{
        public abstract void PostComment(String userName,String EquipNum,String Comment,String type);
    }
    interface PropertyModel extends BaseModel{
        Observable<Webservice<String>> PostComment(String userName,String EquipNum,String Comment,String type);
    }
    interface PropertyMview extends BaseView{
        void backPre();
        void ShowDialog(String message);
    }
}

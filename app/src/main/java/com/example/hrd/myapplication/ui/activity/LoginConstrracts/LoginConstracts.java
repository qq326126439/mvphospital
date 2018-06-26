package com.example.hrd.myapplication.ui.activity.LoginConstrracts;

import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import io.reactivex.Observable;

public interface LoginConstracts {
    public abstract class LoginPresenter extends BasePresenter<LoginModel,LoginMview>{
        public abstract void Login(String url);
    }

     interface LoginMview extends BaseView{

    }
     interface LoginModel extends BaseModel{
         Observable<String> Login(String url);
    }
}

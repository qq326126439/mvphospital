package com.example.gongshihao.myapplication.ui.activity.LoginConstrracts;

import com.example.gongshihao.myapplication.mvp.BaseModel;
import com.example.gongshihao.myapplication.mvp.BasePresenter;
import com.example.gongshihao.myapplication.mvp.BaseView;

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

package com.example.hrd.myapplication.ui.activity.LoginConstrracts;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends LoginConstracts.LoginPresenter{
    @Override
    public void Login(String url) {
        mModel.Login(url).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mView.updateData("");
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onStart() {

    }
}

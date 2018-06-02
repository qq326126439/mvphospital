package com.example.gongshihao.myapplication.ui.activity.MainActivityConstracts;

import com.example.gongshihao.myapplication.bean.MainMenuBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gongshihao on 2018/4/17.
 */

public class MainPresenter extends MainConstracts.MainPresenter{

    @Override
    public void getMainMenu() {
        mModel.getMainMenu().subscribe(new Observer<List<MainMenuBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MainMenuBean> mainMenuBeans) {
                mView.EquipAdapter(mainMenuBeans);
            }

            @Override
            public void onError(Throwable e) {

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

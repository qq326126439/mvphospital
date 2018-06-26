package com.example.hrd.myapplication.ui.activity.MainActivityConstracts;



import com.example.hrd.myapplication.bean.MainMenuBean;
import com.example.hrd.myapplication.mvp.BaseModel;
import com.example.hrd.myapplication.mvp.BasePresenter;
import com.example.hrd.myapplication.mvp.BaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by gongshihao on 2018/4/17.
 */

public interface MainConstracts {

    public abstract class MainPresenter extends BasePresenter<MainModel,MainMview>{
        public abstract void getMainMenu();
    }

    interface MainMview extends BaseView{
        void EquipAdapter(List<MainMenuBean> mList);
    }
    interface MainModel extends BaseModel{
        Observable<List<MainMenuBean>> getMainMenu();
    }
}
